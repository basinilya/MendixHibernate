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

public class GenerateMendixJdbcProxies {

    private static final String GENENTITIES_PACKAGE = "mxhibernate.genentities";

    private static final Map<String, Map<String, String>> assocsByEntityName = new HashMap<>();

    private GenerateMendixJdbcProxies() {
        //
    }

    public static void main(final String[] args) throws Exception {

        // extracted(X.class);
        // System.exit(0);

        final File fBpeSources0 = new File("./src").getAbsoluteFile();
        final File fBpeSources = new File(fBpeSources0, GENENTITIES_PACKAGE.replace('.', '/'));
        if (!fBpeSources.isDirectory()) {
            throw new FileNotFoundException(fBpeSources.getPath());
        }

        final File fBpClasses =
            new File("./DummyMendixApp/deployment/run/bin").getAbsoluteFile();
        if (!fBpClasses.isDirectory()) {
            throw new FileNotFoundException(fBpClasses.getPath());
        }
        final URL bpClasses = fBpClasses.toURI().toURL();

        final List<String> proxyClassNames = findProxyClasses(fBpClasses);

        deleteProxySources(fBpeSources);

        final URL[] urls = new URL[] { bpClasses };
        try (URLClassLoader urlClassLoader = new URLClassLoader(urls);) {
            for (final String proxyClassName : proxyClassNames) {
                collectAssociations(fBpeSources0, urlClassLoader, proxyClassName);
            }
            System.out.println(assocsByEntityName);
            System.exit(0);
            for (final String proxyClassName : proxyClassNames) {
                generateJavaFile(fBpeSources0, urlClassLoader, proxyClassName);
            }
        }
        System.out.println("done");
    }

    private static void collectAssociations(
            final File fBpeSources0,
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

        extracted(urlClassLoader, proxyClass);

    }

    private static void extracted(final URLClassLoader urlClassLoader, final Class<?> proxyClass)
                                                             throws IntrospectionException,
                                                                 NoSuchFieldException,
                                                                 IllegalArgumentException,
                                                                 IllegalAccessException,
                                                                 SecurityException {

        if (proxyClass.getName().startsWith("system") || proxyClass
            .getName()
            .startsWith("administration")) {
            return;
        }

        final HashMap<String, String> assocs = new HashMap<>();
        assocsByEntityName.put((String) proxyClass.getField("entityName").get(null), assocs);

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
            try {
                associatedEntityName = (String) elementClass.getField("entityName").get(null);
            } catch (final NoSuchFieldException e) {
                e.printStackTrace();
                continue;
            }

            final String propName = pd.getName();
            final Enum<?> enuAssoc = mxMembersByBeanProp.get(propName);

            assocs.put(propName, associatedEntityName);

            // System.out.println(propName + ": " + propertyType);
            // propertyType.toString();
        }
    }

    private static void generateJavaFile(
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

        final StringBuilder sb = new StringBuilder();

        final String[] split = convertProxyClassName(proxyClassName);

        final String newPackage = split[0];
        final String dbClassSimpleName = split[1];

        sb.append("/* generated with: ").append(GenerateMendixJdbcProxies.class.getName()).append(" */");
        sb.append("\n").append("package ").append(newPackage).append(";");
        sb.append("\n");
        final boolean isEnum = proxyClass.isEnum();
        sb
            .append("\n")
            .append("public ")
            .append(isEnum ? "enum" : "class")
            .append(" ")
            .append(dbClassSimpleName);

        if (proxyClass.isEnum()) {
            sb.append("\n").append("{");

            appendEnumClassBody(sb, proxyClass, dbClassSimpleName);

        } else {

            if (!"java.lang.Object".equals(proxySuperClassName)) {
                final String[] split2 = convertProxyClassName(proxySuperClassName);
                final String newPackage2 = split2[0];
                final String dbClassSimpleName2 = split2[1];
                sb.append(" extends ").append(newPackage2).append(".").append(dbClassSimpleName2);
            }

            sb.append("\n").append("{");

            appendClassBody(sb, urlClassLoader, proxyClassName, proxyClass);

            final BeanInfo info = Introspector.getBeanInfo(proxyClass);
            final PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; ++i) {
                final PropertyDescriptor pd = propertyDescriptors[i];
                pd.getName();
            }

        }

        sb.append("\n").append("}");
        sb.append("\n");

        final File fNewPackage = new File(fBpeSources0, newPackage.replace('.', '/'));
        Files.createDirectories(fNewPackage.toPath());

        final File fNewSource = new File(fNewPackage, dbClassSimpleName + ".java");

        try (
            OutputStreamWriter out =
                new OutputStreamWriter(new FileOutputStream(fNewSource), StandardCharsets.UTF_8);) {
            out.write(sb.toString());
        }
    }

    private static void appendClassBody(
            final StringBuilder sb,
            final URLClassLoader urlClassLoader,
            final String proxyClassName,
            final Class<?> proxyClass) throws NoSuchFieldException, IllegalAccessException {
        final Field fEntityName = proxyClass.getDeclaredField("entityName");
        final String entityName = (String) fEntityName.get(null);
        final String escEntity = StringEscapeUtils.escapeJava(entityName);

        sb
            .append("\n")
            .append("    public static final java.lang.String entityName = \"")
            .append(escEntity)
            .append("\";");

        final Class<Enum<?>> memberNamesClass = getMemberNamesClass(urlClassLoader, proxyClassName);
        if (memberNamesClass != null) {
            sb.append("\n");
            sb.append("\n").append("    public enum MemberNames");
            sb.append("\n").append("    {");
            final Enum<?>[] enums = memberNamesClass.getEnumConstants();
            String esep = "\n";
            for (final Enum<?> enu : enums) {
                final String name = enu.name();
                final String value = enu.toString();
                final String escVal = StringEscapeUtils.escapeJava(value);
                sb
                    .append(esep)
                    .append("        ")
                    .append(name)
                    .append("(\"")
                    .append(escVal)
                    .append("\")");
                esep = ",\n";
            }
            sb.append(";");
            sb.append("\n");
            sb
                .append(
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
                        + "    }\n" //
                );
        }
    }

    private static void appendEnumClassBody(
            final StringBuilder sb,
            final Class<?> proxyClass,
            final String dbClassSimpleName) throws NoSuchFieldException, IllegalAccessException {
        final Enum<?>[] enums = (Enum<?>[]) proxyClass.getEnumConstants();
        final Field fCaptions = proxyClass.getDeclaredField("captions");
        fCaptions.setAccessible(true);

        String esep = "\n";

        for (final Enum<?> enu : enums) {
            final String name = enu.name();
            @SuppressWarnings("unchecked")
            final
            Map<String, String> captions = (Map<String, String>) fCaptions.get(enu);

            sb.append(esep).append("    ").append(name).append("(new java.lang.String[][] { ");
            esep = ",\n";

            String sep = "";
            for (final Entry<String, String> pair : captions.entrySet()) {
                final String escKey = StringEscapeUtils.escapeJava(pair.getKey());
                final String escVal = StringEscapeUtils.escapeJava(pair.getValue());
                sb
                    .append(sep)
                    .append("new java.lang.String[] { \"")
                    .append(escKey)
                    .append("\", \"")
                    .append(escVal)
                    .append("\" }");
                sep = ", ";
            }
            sb.append(" })");
        }
        sb.append(";");

        sb.append("\n");
        sb
            .append(
                "\n" //
                    + "    private final java.util.Map<java.lang.String, java.lang.String> captions;\n"
                    + "\n" //
                    + "    private ")
            .append(dbClassSimpleName)
            .append(
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
        final String sMxModuleDir = split[0];
        final String proxyClassSimpleName = split[1];
        final String dbClassSimpleName = "Db" + proxyClassSimpleName;
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

    private static final class MyDeletingVisitor extends SimpleFileVisitor<Path> {

        private final Path pBpeSources;

        private MyDeletingVisitor(final Path pBpeSources) {
            this.pBpeSources = pBpeSources;
        }

        @Override
        public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
            super.visitFile(file, attrs);
            if (file.toString().endsWith(".java")) {
                Files.delete(file);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
            super.postVisitDirectory(dir, exc);
            if (!dir.equals(pBpeSources) && PathUtils.isEmptyDirectory(dir)) {
                Files.delete(dir);
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
