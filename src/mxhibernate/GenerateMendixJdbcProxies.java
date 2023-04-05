package mxhibernate;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

/**
 * Generate Enums and Hibernate POJOs out of Mendix proxy classes. The classes have no annotations
 * because physical table and column names differ among environments. The actual mapping XML is
 * generated with {@link MendixHibernate} <br>
 * A Mendix proxy class name is transformed so the word ".proxy" is removed from the package name
 * and the simple class name gets prefixed with the word "Db"<br>
 * All existing java files in the target package will be deleted.<br>
 * The hidden system entities like System.WorkflowVersion, hidden associations like System.owner,
 * and hidden attributes like changedDate and __FileName__ are not supported yet.<br>
 * For simplicity, all associations are considered many-to-many.
 */
public class GenerateMendixJdbcProxies {

    /** Java sources root for the generated sources */
    private static final String GENENTITIES_ROOT_DIR = "./src";

    /** Base Java package for the generated sources */
    public static final String GENENTITIES_PACKAGE = "mxhibernate.genentities";

    private static final String COMPILED_CLASSES_DIR = "./DummyMendixApp/deployment/run/bin";

    /** Name suffix for the second bean property of a self-reference */
    public static final String SUFFIX_REVERSE = "_reverse";

    private static final String CLASSNAME_PREFIX = "Db";

    private final Map<String, Map<String, String>> assocsByEntityName = new HashMap<>();

    private final StringBuilder sb = new StringBuilder();

    private GenerateMendixJdbcProxies() {
        //
    }

    public static void main(final String[] args) throws Exception {
        final GenerateMendixJdbcProxies instance = new GenerateMendixJdbcProxies();
        instance.doIt();
    }

    private void doIt() throws Exception {

        final File fBpeSources0 = new File(GENENTITIES_ROOT_DIR).getAbsoluteFile();
        final File fBpeSources = new File(fBpeSources0, GENENTITIES_PACKAGE.replace('.', '/'));
        if (!fBpeSources.isDirectory()) {
            throw new FileNotFoundException(fBpeSources.getPath());
        }

        final File fBpClasses = new File(COMPILED_CLASSES_DIR).getAbsoluteFile();
        if (!fBpClasses.isDirectory()) {
            throw new FileNotFoundException(fBpClasses.getPath());
        }
        final URL bpClasses = fBpClasses.toURI().toURL();

        final List<String> proxyClassNames = findProxyClasses(fBpClasses);

        deleteProxySources(fBpeSources);

        final URL[] urls = new URL[] { bpClasses };
        try (URLClassLoader urlClassLoader = new URLClassLoader(urls);) {
            for (final String proxyClassName : proxyClassNames) {
                collectAssociations(urlClassLoader, proxyClassName);
            }
            for (final String proxyClassName : proxyClassNames) {
                generateJavaFile(fBpeSources0, urlClassLoader, proxyClassName);
            }
        }
        System.out.println("done");
    }

    private void collectAssociations(
            final URLClassLoader urlClassLoader,
            final String proxyClassName)
                                         throws ClassNotFoundException,
                                             NoSuchFieldException,
                                             IllegalAccessException,
                                             IOException,
                                             IntrospectionException {
        final Class<?> proxyClass = urlClassLoader.loadClass(proxyClassName);

        final boolean isEnum = proxyClass.isEnum();
        if (isEnum) {
            return;
        }

        final String entityName = getEntityName(proxyClass);
        final Map<String, String> assocs = getAssocs(entityName);

        final Class<Enum<?>> memberNamesClass =
            getMemberNamesClass(urlClassLoader, proxyClass.getName());
        if (memberNamesClass == null) {
            return;
        }
        final Map<String, Enum<?>> mxMembersByBeanProp =
            Arrays
                .stream(memberNamesClass.getEnumConstants())
                .filter(enu -> enu.toString().indexOf('.') != -1)
                .collect(
                    Collectors
                        .toMap(
                            enu -> Introspector
                                .decapitalize(StringUtils.split(enu.toString(), '.')[1]),
                            Function.identity()));

        final BeanInfo info = Introspector.getBeanInfo(proxyClass);
        final PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; ++i) {
            final PropertyDescriptor pd = propertyDescriptors[i];
            final Class<?> propertyType = pd.getPropertyType();

            if (propertyType.isArray()) {
                continue;
            }

            final Method readMethod = pd.getReadMethod();
            if (readMethod == null) {
                continue;
            }

            if (!proxyClass.equals(readMethod.getDeclaringClass())) {
                continue;
            }

            final Class<?> elementClass;
            if (Collection.class.isAssignableFrom(propertyType)) {
                // 1:* or *:*
                final ParameterizedType genericReturnType =
                    (ParameterizedType) readMethod.getGenericReturnType();
                final Type elementType = genericReturnType.getActualTypeArguments()[0];
                if (!(elementType instanceof Class)) {
                    continue;
                }
                elementClass = (Class<?>) elementType;
                if (elementClass.isEnum()) {
                    continue;
                }

            } else {
                // 1:1
                elementClass = propertyType;
            }

            if (elementClass.isEnum()) {
                continue;
            }
            final String elementClassName = elementClass.getName();

            if (!elementClassName.matches("^[^.]+\\.proxies\\.[^.$]+$")) {
                continue;
            }

            final String associatedEntityName;
            associatedEntityName = getEntityName(elementClass);

            final String propName = pd.getName();
            // final Enum<?> enuAssoc = mxMembersByBeanProp.get(propName);

            assocs.put(propName, associatedEntityName);

            final Map<String, String> otherSide = getAssocs(associatedEntityName);
            otherSide.put(propName, entityName);
        }
    }

    private Map<String, String> getAssocs(final String entityName) {
        return assocsByEntityName.computeIfAbsent(entityName, unused -> new HashMap<>());
    }

    private void generateJavaFile(
            final File fBpeSources0,
            final URLClassLoader urlClassLoader,
            final String proxyClassName)
                                         throws ClassNotFoundException,
                                             NoSuchFieldException,
                                             IllegalAccessException,
                                             IOException,
                                             IntrospectionException {
        final Class<?> proxyClass = urlClassLoader.loadClass(proxyClassName);

        final Class<?> proxySuperClass = proxyClass.getSuperclass();
        final String proxySuperClassName = proxySuperClass.getName();

        sb.setLength(0);

        final String[] split = convertProxyClassName(proxyClassName);

        final String newPackage = split[0];
        final String dbClassSimpleName = split[1];

        this.a("/* generated with: ").a(GenerateMendixJdbcProxies.class.getName()).a(" */");
        this.a("\n").a("package ").a(newPackage).a(";");
        this.a("\n");
        final boolean isEnum = proxyClass.isEnum();
        this.a("\n").a("public ").a(isEnum ? "enum" : "class").a(" ").a(dbClassSimpleName);

        if (proxyClass.isEnum()) {
            this.a("\n").a("{");

            appendEnumClassBody(proxyClass, dbClassSimpleName);

        } else {

            final boolean isRootEntity = "java.lang.Object".equals(proxySuperClassName);
            if (!isRootEntity) {
                final String[] split2 = convertProxyClassName(proxySuperClassName);
                final String newPackage2 = split2[0];
                final String dbClassSimpleName2 = split2[1];
                this.a(" extends ").a(newPackage2).a(".").a(dbClassSimpleName2);
            }

            this.a("\n").a("{");

            appendClassBody(urlClassLoader, proxyClassName, proxyClass, isRootEntity);

        }

        this.a("\n").a("}");
        this.a("\n");

        final File fNewPackage = new File(fBpeSources0, newPackage.replace('.', '/'));
        Files.createDirectories(fNewPackage.toPath());

        final File fNewSource = new File(fNewPackage, dbClassSimpleName + ".java");

        try (
            OutputStreamWriter out =
                new OutputStreamWriter(new FileOutputStream(fNewSource), StandardCharsets.UTF_8);) {
            out.write(sb.toString());
        }
    }

    private void appendClassBody(
            final URLClassLoader urlClassLoader,
            final String proxyClassName,
            final Class<?> proxyClass,
            final boolean isRootEntity)
                                        throws NoSuchFieldException,
                                            IllegalAccessException,
                                            IntrospectionException {
        final Field fEntityName = proxyClass.getDeclaredField("entityName");
        final String entityName = (String) fEntityName.get(null);
        final String escEntity = StringEscapeUtils.escapeJava(entityName);

        this
            .a("\n")
            .a("    public static final java.lang.String entityName = \"")
            .a(escEntity)
            .a("\";");

        final Class<Enum<?>> memberNamesClass = getMemberNamesClass(urlClassLoader, proxyClassName);
        if (memberNamesClass == null) {
            return;
        }

        this.a("\n");
        this.a("\n").a("    public enum MemberNames");
        this.a("\n").a("    {");
        final Enum<?>[] enums = memberNamesClass.getEnumConstants();
        String esep = "\n";
        for (final Enum<?> enu : enums) {
            final String name = enu.name();
            final String value = enu.toString();
            final String escVal = StringEscapeUtils.escapeJava(value);
            this.a(esep).a("        ").a(name).a("(\"").a(escVal).a("\")");
            esep = ",\n";
        }
        this.a(";");
        this.a("\n");
        this
            .a(
                "\n" //
                    + "        private final java.lang.String metaName;\n" //
                    + "\n" + "        MemberNames(java.lang.String s)\n" //
                    + "        {\n" //
                    + "            metaName = s;\n" //
                    + "        }\n" //
                    + "\n" + "        @java.lang.Override\n"
                    + "        public java.lang.String toString()\n" //
                    + "        {\n" + "            return metaName;\n" //
                    + "        }\n" //
                    + "    }" //
            );

        if (isRootEntity) {
            appendPropMethods("long", "getId", "setId", "id");
        }

        final Map<String, Enum<?>> mxMembersByBeanProp =
            Arrays
                .stream(memberNamesClass.getEnumConstants())
                .collect(
                    Collectors.toMap(enu -> assocToPropName(enu.toString()), Function.identity()));

        final BeanInfo info = Introspector.getBeanInfo(proxyClass);
        final PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; ++i) {
            final PropertyDescriptor pd = propertyDescriptors[i];

            final Class<?> propertyType = pd.getPropertyType();

            if (propertyType.isArray()) {
                continue;
            }

            final Method readMethod = pd.getReadMethod();

            // hashed string only has a setter
            final Method anyMethod = readMethod == null ? pd.getWriteMethod() : readMethod;
            if (anyMethod == null) {
                continue;
            }

            if (!proxyClass.equals(anyMethod.getDeclaringClass())) {
                continue;
            }

            if (Collection.class.isAssignableFrom(propertyType)) {
                // 1:* or *:*
                continue;
            }

            // 1:1 or regular property
            final Class<?> propClass = propertyType;

            final String propClassName = propClass.getName();

            final boolean propIsProxy = propClassName.matches("^[^.]+\\.proxies\\.[^.$]+$");

            if (!propClass.isEnum() && propIsProxy) {
                // reference
                continue;
            }

            final String propName = pd.getName();

            final Enum<?> enuMember = mxMembersByBeanProp.get(propName);

            if (enuMember == null) {
                continue;
            }

            // no enums
            final String newPropTypeName = propIsProxy ? "java.lang.String" : propClassName;

            final String baseName = StringUtils.capitalize(propName);
            final String getterName = "get" + baseName;
            final String setterName = "set" + baseName;

            appendPropMethods(newPropTypeName, getterName, setterName, propName);

        }

        final Map<String, String> assocs = assocsByEntityName.get(entityName);
        for (final Entry<String, String> pair : assocs.entrySet()) {
            final String propName = pair.getKey();
            final String otherEntity = pair.getValue();

            final String otherClassName = entityNameToNewClassName(otherEntity);
            final String newPropTypeName = "java.util.Set<" + otherClassName + ">";

            final String baseName = StringUtils.capitalize(propName);
            final String getterName = "get" + baseName;
            final String setterName = "set" + baseName;

            appendPropMethods(newPropTypeName, getterName, setterName, propName);

            if (otherEntity.equals(entityName)) {
                appendPropMethods(
                    newPropTypeName,
                    getterName + SUFFIX_REVERSE,
                    setterName + SUFFIX_REVERSE,
                    propName + SUFFIX_REVERSE);
            }

        }

    }

    public static String assocToPropName(final String assoc) {
        return Introspector.decapitalize(assoc.replaceFirst(".*\\.", ""));
    }

    public static String entityNameToNewClassName(final String entityName) {
        final String[] a = convert2(StringUtils.split(entityName, '.'));
        return a[0] + '.' + a[1];
    }

    private void appendPropMethods(
            final String propTypeName,
            final String getterName,
            final String setterName,
            final String javaPropName) {
        this
            .a(
                "\n"//
                    + "\n" + "    private ")
            .a(propTypeName)
            .a(" ")
            .a(javaPropName)
            .a(";");

        this
            .a(
                "\n"//
                    + "\n" + "    public ")
            .a(propTypeName)
            .a(" ")
            .a(getterName)
            .a(
                "() {" //
                    + "\n" + "        return this.")
            .a(javaPropName)
            .a(
                ";" //
                    + "\n" + "    }");

        this
            .a(
                "\n"//
                    + "\n" + "    public void ")
            .a(setterName)
            .a("(")
            .a(propTypeName)
            .a(
                " val) {" //
                    + "\n" + "        this.")
            .a(javaPropName)
            .a(
                " = val;" //
                    + "\n" + "    }");
    }

    private void appendEnumClassBody(final Class<?> proxyClass, final String dbClassSimpleName)
                                                                                                throws NoSuchFieldException,
                                                                                                    IllegalAccessException {
        final Enum<?>[] enums = (Enum<?>[]) proxyClass.getEnumConstants();
        final Field fCaptions = proxyClass.getDeclaredField("captions");
        fCaptions.setAccessible(true);

        String esep = "\n";

        for (final Enum<?> enu : enums) {
            final String name = enu.name();
            @SuppressWarnings("unchecked")
            final Map<String, String> captions = (Map<String, String>) fCaptions.get(enu);

            this.a(esep).a("    ").a(name).a("(new java.lang.String[][] { ");
            esep = ",\n";

            String sep = "";
            for (final Entry<String, String> pair : captions.entrySet()) {
                final String escKey = StringEscapeUtils.escapeJava(pair.getKey());
                final String escVal = StringEscapeUtils.escapeJava(pair.getValue());
                this
                    .a(sep)
                    .a("new java.lang.String[] { \"")
                    .a(escKey)
                    .a("\", \"")
                    .a(escVal)
                    .a("\" }");
                sep = ", ";
            }
            this.a(" })");
        }
        this.a(";");

        this.a("\n");
        this
            .a(
                "\n" //
                    + "    private final java.util.Map<java.lang.String, java.lang.String> captions;\n"
                    + "\n" //
                    + "    private ")
            .a(dbClassSimpleName)
            .a(
                "(java.lang.String[][] captionStrings)\n"//
                    + "    {\n" //
                    + "        this.captions = new java.util.HashMap<>();\n"
                    + "        for (java.lang.String[] captionString : captionStrings) {\n"
                    + "            captions.put(captionString[0], captionString[1]);\n"
                    + "        }\n" //
                    + "    }\n" //
                    + "\n"
                    + "    public java.lang.String getCaption(java.lang.String languageCode)\n"
                    + "    {\n" //
                    + "        return captions.getOrDefault(languageCode, \"en_US\");\n" + "    }\n" //
                    + "\n" //
                    + "    public java.lang.String getCaption()\n" //
                    + "    {\n" + "        return captions.get(\"en_US\");\n" //
                    + "    }\n");
    }

    private static String getEntityName(final Class<?> proxyClass)
                                                                   throws IllegalAccessException,
                                                                       NoSuchFieldException {
        return (String) proxyClass.getField("entityName").get(null);
    }

    private static void deleteProxySources(final File fBpeSources) throws IOException {
        // delete .java files and empty subdirs
        // keep source control files
        final Path pBpeSources = fBpeSources.toPath();
        Files.walkFileTree(pBpeSources, new MyDeletingVisitor(pBpeSources));
    }

    @SuppressWarnings("unchecked")
    private static Class<Enum<?>> getMemberNamesClass(
            final URLClassLoader urlClassLoader,
            final String proxyClassName) {
        try {
            return (Class<Enum<?>>) urlClassLoader.loadClass(proxyClassName + "$MemberNames");
        } catch (final ClassNotFoundException /* NOSONAR */ e) {
            return null;
        }
    }

    static String[] convertProxyClassName(final String proxyClassName) {

        final String[] split = StringUtils.splitByWholeSeparator(proxyClassName, ".proxies.");
        return convert2(split);
    }

    private static String[] convert2(final String[] split) {
        final String sMxModuleDir = split[0].toLowerCase();
        final String proxyClassSimpleName = split[1];
        final String dbClassSimpleName = CLASSNAME_PREFIX + proxyClassSimpleName;
        final String newPackage = GENENTITIES_PACKAGE + "." + sMxModuleDir;
        split[0] = newPackage;
        split[1] = dbClassSimpleName;
        return split;
    }

    private static List<String> findProxyClasses(final File fBpClasses) {
        final List<String> res = new ArrayList<>();
        for (final String sMxModuleDir : fBpClasses.list()) {
            final File proxiesDir = new File(fBpClasses, sMxModuleDir + "/proxies");
            final String[] outerClassFiles =
                proxiesDir.list((unused, x) -> x.endsWith("class") && !x.contains("$"));
            if (outerClassFiles != null) {
                final String pkgName = sMxModuleDir + ".proxies";
                for (final String outerClassFile : outerClassFiles) {
                    final String clazzName =
                        pkgName + "." + FilenameUtils.removeExtension(outerClassFile);
                    res.add(clazzName);
                }
            }
        }
        return res;
    }

    private GenerateMendixJdbcProxies a(final Object o) {
        sb.append(o);
        return this;
    }

    private static final class MyDeletingVisitor extends SimpleFileVisitor<Path> {

        private final Path pBpeSources;

        private MyDeletingVisitor(final Path pBpeSources) {
            this.pBpeSources = pBpeSources;
        }

        @Override
        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                                                                                           throws IOException {
            super.visitFile(file, attrs);
            if (file.toString().endsWith(".java")) {
                Files.delete(file);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(final Path dir, final IOException exc)
                                                                                         throws IOException {
            super.postVisitDirectory(dir, exc);
            if (!dir.equals(pBpeSources) && PathUtils.isEmptyDirectory(dir)) {
                Files.delete(dir);
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
