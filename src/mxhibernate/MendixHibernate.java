package mxhibernate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.archive.scan.spi.ScanEnvironment;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import mxhibernate.entities.DbUser;

public class MendixHibernate {

    private static String jdbcUrl;

    private static String user;

    private static String password;

    public static void main(final String[] args) throws Exception {
        if (args.length == 0) {
            initLocalDbDetails();
        } else {
            initExplicitDbDetails(args[0], args[1], args[2]);
        }

        executeWithMxDataSource(MendixHibernate::customLogic);
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

    private static void customLogic0(final DataSource dataSource) throws SQLException {
        final List<Long> userIds = logUsersWithPureJdbc(dataSource);
        logUsersWithHibernate(dataSource, userIds);
    }

    private static List<Long> logUsersWithPureJdbc(final DataSource dataSource)
                                                                                throws SQLException {
        final List<Long> res = new ArrayList<>();
        try (
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs =
                stmt
                    .executeQuery(
                        "select \"id\",\"submetaobjectname\",\"name\" from \"system$user\"");) {
            System.out.println("contents of system$user:");
            final ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 0; i < 10 && rs.next(); i++) {
                for (int iCol = 1, nCols = metaData.getColumnCount(); iCol <= nCols; iCol++) {
                    System.out
                        .print("(" + metaData.getColumnName(iCol) + ":" + rs.getString(iCol) + ")");
                }
                res.add(rs.getLong("id"));
                System.out.println();
            }
            if (rs.next()) {
                System.out.println("...");
            }
            System.out.println();
        }
        return res;
    }

    private static void logUsersWithHibernate(final DataSource dataSource, final List<Long> userIds)
                                                                                                     throws SQLException {
        try (final SessionFactory sessionFactory = makeSessionFactoryBuilder(dataSource).build();) {
            final EntityManager entityManager = sessionFactory.createEntityManager();
            final Metamodel metamodel = entityManager.getMetamodel();
            logKnownEntities(metamodel);
            logAllMxUsersWithIds(userIds, entityManager);
            logAllMxUsersByQuery(entityManager);
        }
    }

    private static void logAllMxUsersWithIds(
            final List<Long> userIds,
            final EntityManager entityManager) {
        System.out.println("Users by Ids:");
        for (final Long userId : userIds) {
            final DbUser obj = entityManager.find(DbUser.class, userId);
            logObject(entityManager, obj);
        }
        System.out.println();
    }

    private static void logKnownEntities(final Metamodel metamodel) {
        for (final EntityType<?> entity : metamodel.getEntities()) {
            System.out.println("Known entity: " + entity.getName());
        }
        System.out.println();
    }

    private static void logAllMxUsersByQuery(final EntityManager entityManager) {
        System.out.println("Users by Query:");
        final String entityName = getEntityName(entityManager, DbUser.class);
        final TypedQuery<DbUser> query =
            entityManager.createQuery("select a from `" + entityName + "` a", DbUser.class);
        final List<DbUser> objs = query.getResultList();
        for (final DbUser obj : objs) {
            logObject(entityManager, obj);
        }
    }

    private static void logObject(final EntityManager entityManager, final DbUser obj) {
        System.out
            .println(getObjEntityName(entityManager, obj) + ": " + obj.getId() + "," + obj.getName());
    }

    private static String getObjEntityName(final EntityManager entityManager, final DbUser obj) {
        return entityManager.getMetamodel().entity(obj.getClass()).getName();
    }

    private static String getEntityName(final EntityManager entityManager, final Class<?> clazz) {
        return entityManager.getMetamodel().entity(clazz).getName();
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

    private static SessionFactoryBuilder makeSessionFactoryBuilder(final DataSource dataSource) {

        final ClassLoaderServiceImpl classLoaderService =
            new ClassLoaderServiceImpl(DbUser.class.getClassLoader());

        final ServiceRegistry standardRegistry =
            new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, true)
                .applySetting(
                    AvailableSettings.HBM2DDL_AUTO,
                    org.hibernate.tool.schema.Action.VALIDATE.getExternalHbm2ddlName())
                .applySetting(AvailableSettings.DATASOURCE, dataSource)
                .addService(ClassLoaderService.class, classLoaderService)
                .build();

        final MetadataSources sources = new MetadataSources(standardRegistry);

        final MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

        metadataBuilder.applyScanEnvironment(new HiberNativeScanEnvironment());

        final Metadata metadata = metadataBuilder.build();
        return metadata.getSessionFactoryBuilder();
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

    /** required to scan the packages in native Hibernate (no JPA) */
    private static class HiberNativeScanEnvironment implements ScanEnvironment {

        @Override
        public URL getRootUrl() {
            return null;
        }

        @Override
        public List<URL> getNonRootUrls() {
            final URL url = DbUser.class.getResource("");
            return Collections.singletonList(url);
        }

        @Override
        public List<String> getExplicitlyListedClassNames() {
            return Collections.emptyList();
        }

        @Override
        public List<String> getExplicitlyListedMappingFiles() {
            return Collections.emptyList();
        }
    }
}
