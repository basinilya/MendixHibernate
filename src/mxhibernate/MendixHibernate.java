package mxhibernate;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
import jakarta.persistence.Query;
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
        logUsersWithPureJdbc(dataSource);
        logUsersWithHibernate(dataSource);
    }

    private static void logUsersWithPureJdbc(final DataSource dataSource) throws SQLException {
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
                    System.out.print("(" + metaData.getColumnName(iCol) + ":" + rs.getString(iCol) + ")");
                }
                System.out.println();
            }
            if (rs.next()) {
                System.out.println("...");
            }
            System.out.println();
        }
    }

    private static void logUsersWithHibernate(final DataSource dataSource) {
        try (final SessionFactory sessionFactory = makeSessionFactoryBuilder(dataSource).build();) {
            final EntityManager entityManager = sessionFactory.createEntityManager();
            final Metamodel metamodel = entityManager.getMetamodel();
            logKnownEntities(metamodel);
            retrieveAllMxUsers(entityManager);
        }
    }

    private static void logKnownEntities(final Metamodel metamodel) {
        for (final EntityType<?> entity : metamodel.getEntities()) {
            System.out.println("Known entity: " + entity.getName());
        }
        System.out.println();
    }

    private static void retrieveAllMxUsers(final EntityManager entityManager) {
        final Query query = entityManager.createQuery("select System.User from System.User");
        @SuppressWarnings("unchecked")
        final List<DbUser> objs = query.getResultList();
        for (final DbUser obj : objs) {
            System.out
                .println(
                    entityManager.getMetamodel().entity(obj.getClass()).getName() + ": " + obj
                        .getName());
        }
    }

    private static String getLocalDbJdbcUrl() {
        final StringBuilder serverUrl = new StringBuilder();
        serverUrl.append("file:");
        final String databaseName = "default";
        final String child = "hsqldb/" + databaseName + "/" + databaseName;
        final String databasePath =
            new File("DummyMendixApp/deployment/data/database", child).toString();
        serverUrl.append(databasePath).append(";ifexists=true;readonly=true;");

        return "jdbc:hsqldb:" + serverUrl.toString();
    }

    private static SessionFactoryBuilder makeSessionFactoryBuilder(final DataSource dataSource) {

        final ClassLoaderServiceImpl classLoaderService =
            new ClassLoaderServiceImpl(DbUser.class.getClassLoader());

        final ServiceRegistry standardRegistry =
            new StandardServiceRegistryBuilder()
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

    private static void initLocalDbDetails() {
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
            final URL url = DbUser.class.getProtectionDomain().getCodeSource().getLocation();
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
