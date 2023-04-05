package mxhibernate.util;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

import javax.sql.DataSource;

import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.zaxxer.hikari.HikariDataSource;

import mxhibernate.util.JdbcEntities.Entity;

/**
 * TODO: this is supposed to supplement GenerateMendixJdbcProxies with the hidden system entities
 * like System.WorkflowVersion, hidden associations like System.owner, and hidden attributes like
 * changedDate and __FileName__ <br>
 * On the other hand, the DB does not contain the info about Enums, but the Mendix proxies do.
 */
public class GenerateMendixJdbcProxiesFromDb {

    private static final String GENENTITIES_PACKAGE = "mxhibernate.genentities";

    private static String jdbcUrl;

    private static String user;

    private static String password;

    private GenerateMendixJdbcProxiesFromDb() {
        //
    }

    public static void main_disabled(final String[] args) throws Exception {

        if (args.length == 0) {
            initLocalDbDetails();
        } else {
            initExplicitDbDetails(args[0], args[1], args[2]);
        }

        executeWithMxDataSource(GenerateMendixJdbcProxiesFromDb::customLogic);

        System.out.println("done");
    }

    private static void customLogic(final DataSource dataSource) {
        try {
            customLogic0(dataSource);
        } catch (final Throwable e) {
            // log error before DS logs shutdown
            e.printStackTrace();
            if (e instanceof Error) {
                throw (Error) e;
            }
        }
    }

    private static void customLogic0(final DataSource dataSource) throws Exception {
        try (Connection conn = dataSource.getConnection();) {
            final JdbcEntities jdbcEntities = JdbcEntities.getInstance(conn);
            jdbcEntities.toString();

            final File fBpeSources0 = new File("./src").getAbsoluteFile();
            final File fBpeSources = new File(fBpeSources0, GENENTITIES_PACKAGE.replace('.', '/'));
            if (!fBpeSources.isDirectory()) {
                throw new FileNotFoundException(fBpeSources.getPath());
            }

            deleteProxySources(fBpeSources);

            for (final Entity entity : jdbcEntities.entitiesByName.values()) {
                generateJavaFile(fBpeSources0, jdbcEntities, entity);
            }
        }
    }

    private static void generateJavaFile(
            final File fBpeSources0,
            final JdbcEntities jdbcEntities,
            final Entity entity)
                                   throws ClassNotFoundException,
                                       NoSuchFieldException,
                                       IllegalAccessException,
                                             IOException,
                                             IntrospectionException {
        final Entity superEntity =
            Optional
                .ofNullable(entity.superentity_id)
                .map(superentity_id -> jdbcEntities.entitiesById.get(superentity_id))
                .orElse(null);

        final StringBuilder sb = new StringBuilder();

        final boolean isEnum = false;

        final String[] split = convertEntityName(entity.entity_name);

        final String newPackage = split[0];
        final String dbClassSimpleName = split[1];

        sb.append("/* generated with: ").append(GenerateMendixJdbcProxiesFromDb.class.getName()).append(" */");
        sb.append("\n").append("package ").append(newPackage).append(";");
        sb.append("\n");
        sb
            .append("\n")
            .append("public ")
            .append(isEnum ? "enum" : "class")
            .append(" ")
            .append(dbClassSimpleName);

        if (isEnum) {
            sb.append("\n").append("{");

            // appendEnumClassBody(sb, proxyClass, dbClassSimpleName);

        } else {

            if (superEntity != null) {
                final String[] split2 = convertEntityName(superEntity.entity_name);
                final String newPackage2 = split2[0];
                final String dbClassSimpleName2 = split2[1];
                sb.append(" extends ").append(newPackage2).append(".").append(dbClassSimpleName2);
            }

            sb.append("\n").append("{");

            appendClassBody(sb, jdbcEntities, entity);

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
            final JdbcEntities jdbcEntities,
            final Entity entity) throws NoSuchFieldException, IllegalAccessException {
        final String entityName = entity.entity_name;
        final String escEntity = StringEscapeUtils.escapeJava(entityName);

        sb
            .append("\n")
            .append("    public static final java.lang.String entityName = \"")
            .append(escEntity)
            .append("\";");

        final boolean noMembers =
            entity.attributesByName.isEmpty() && entity.assocWithParentByName.isEmpty()
                && entity.assocWithChildByName.isEmpty();
        if (!noMembers) {
            sb.append("\n");
            sb.append("\n").append("    public enum MemberNames");
            sb.append("\n").append("    {");

            final LinkedHashSet<String> set = new LinkedHashSet<>();
            set.addAll(entity.attributesByName.keySet());
            set.addAll(entity.assocWithChildByName.keySet());
            set.addAll(entity.assocWithParentByName.keySet());

            String esep = "\n";
            for (final String value : set) {
                final String name = value.replaceFirst(".*\\.", "");
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

    static String[] convertEntityName(final String entityName) {

        final String[] split = StringUtils.splitByWholeSeparator(entityName, ".");
        final String sMxModuleDir = split[0].toLowerCase();
        final String proxyClassSimpleName = split[1];
        final String dbClassSimpleName = "Db" + proxyClassSimpleName;
        final String newPackage = GENENTITIES_PACKAGE + "." + sMxModuleDir;
        split[0] = newPackage;
        split[1] = dbClassSimpleName;
        return split;
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

    /** mimic Mendix Database Connector module */
    private static void executeWithMxDataSource(final Consumer<DataSource> consumer) {
        try (final HikariDataSource dataSource = openDataSource(jdbcUrl, user, password, 0);) {
            consumer.accept(dataSource);
        }
    }

    private static HikariDataSource openDataSource(
            final String jdbcUrl,
            final String userName,
            final String password,
            final Integer connPoolKey) {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName(String.format("MxDbConnector-HikaryCP-%d", connPoolKey));
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setMinimumIdle(0);
        dataSource.setReadOnly(true);

        return dataSource;
    }

    private static void initExplicitDbDetails(
            final String xjdbcUrl,
            final String xuser,
            final String xpassword) {
        jdbcUrl = xjdbcUrl;
        user = xuser;
        password = xpassword;
    }

    private static void initLocalDbDetails() throws IOException {
        jdbcUrl = getLocalDbJdbcUrl();
        user = "SA";
        password = "";
    }

    private static String getLocalDbJdbcUrl() throws IOException {
        final StringBuilder serverUrl = new StringBuilder();
        serverUrl.append("file:");
        final String databaseName = "default";
        final String child = "hsqldb/" + databaseName + "/" + databaseName;
        final File databaseDir = new File("DummyMendixApp/deployment/data/database", child);
        serverUrl.append(databaseDir).append(";ifexists=true;readonly=true;");

        return "jdbc:hsqldb:" + serverUrl.toString();
    }
}
