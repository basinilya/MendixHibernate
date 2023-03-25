package mxhibernate;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.jaxb.Origin;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.boot.jaxb.mapping.JaxbAttributes;
import org.hibernate.boot.jaxb.mapping.JaxbBasic;
import org.hibernate.boot.jaxb.mapping.JaxbColumn;
import org.hibernate.boot.jaxb.mapping.JaxbDiscriminatorColumn;
import org.hibernate.boot.jaxb.mapping.JaxbEmptyType;
import org.hibernate.boot.jaxb.mapping.JaxbEntity;
import org.hibernate.boot.jaxb.mapping.JaxbEntityMappings;
import org.hibernate.boot.jaxb.mapping.JaxbId;
import org.hibernate.boot.jaxb.mapping.JaxbInheritance;
import org.hibernate.boot.jaxb.mapping.JaxbJoinColumn;
import org.hibernate.boot.jaxb.mapping.JaxbJoinTable;
import org.hibernate.boot.jaxb.mapping.JaxbManyToMany;
import org.hibernate.boot.jaxb.mapping.JaxbPersistenceUnitMetadata;
import org.hibernate.boot.jaxb.mapping.JaxbTable;
import org.hibernate.boot.jaxb.spi.Binding;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import mxhibernate.entities.DbAccount;
import mxhibernate.entities.DbUser;
import mxhibernate.entities.DbUserRole;

public class MendixHibernate {

    private static String jdbcUrl;

    private static String user;

    private static String password;

    private static JAXBContext jaxbContext;

    public static JaxbEntityMappings jaxbEntityMappings;

    public static void main(final String[] args) throws Exception {
        jaxbContext = JAXBContext.newInstance(JaxbEntityMappings.class);

        if (args.length == 0) {
            initLocalDbDetails();
        } else {
            initExplicitDbDetails(args[0], args[1], args[2]);
        }

        try {
            executeWithMxDataSource(MendixHibernate::customLogic);
        } finally {
            // dumpconf(jaxbEntityMappings);
        }
    }

    private static void dumpconf(final JaxbEntityMappings obj)
                                                               throws JAXBException,
                                                                   PropertyException {
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, System.out);
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

    private static void customLogic0(final DataSource dataSource) throws SQLException, IOException {
        final List<Long> userIds;
        try (Connection conn = dataSource.getConnection();) {
            userIds = logUsersWithPureJdbc(conn);
            logAccountsWithPureJdbc(conn);
            logRolesWithPureJdbc(conn);
        }
        logUsersWithHibernate(dataSource, userIds);
    }

    private static List<Long> logUsersWithPureJdbc(final Connection conn) throws SQLException {
        return logTable(conn, "system$user", "\"id\",\"submetaobjectname\",\"name\"");
    }

    private static List<Long> logAccountsWithPureJdbc(final Connection conn) throws SQLException {
        return logTable(conn, "administration$account", "*");
    }

    private static List<Long> logRolesWithPureJdbc(final Connection conn) throws SQLException {
        return logTable(conn, "system$userrole", "*");
    }

    private static List<Long> logTable(
            final Connection conn,
            final String table,
            final String columns) throws SQLException {
        final List<Long> res = new ArrayList<>();
        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select " + columns + " from \"" + table + "\"");) {
            System.out.println("contents of " + table + ":");
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
                                                                                                     throws SQLException,
                                                                                                         IOException {
        try (final SessionFactory sessionFactory = makeSessionFactoryBuilder(dataSource).build();) {
            final EntityManager entityManager = sessionFactory.createEntityManager();
            final Metamodel metamodel = entityManager.getMetamodel();
            logKnownEntities(metamodel);
            logAllMxUsersWithIds(userIds, entityManager);
            logAllMxObjectsByQuery(
                entityManager,
                DbUser.class,
                MendixHibernate::logUser,
                "where a.name != 'x'");
            logAllMxObjectsByQuery(
                entityManager,
                DbUserRole.class,
                MendixHibernate::logUserRole,
                "where a.name != 'x'");
        }
    }

    private static void logAllMxUsersWithIds(
            final List<Long> userIds,
            final EntityManager entityManager) {
        System.out.println("Users by Ids:");
        for (final Long userId : userIds) {
            final DbUser obj = entityManager.find(DbUser.class, userId);
            logUser(entityManager, obj);
        }
        System.out.println();
    }

    private static void logKnownEntities(final Metamodel metamodel) {
        for (final EntityType<?> entity : metamodel.getEntities()) {
            System.out.println("Known entity: " + entity.getName());
        }
        System.out.println();
    }

    private static <T> void logAllMxObjectsByQuery(
            final EntityManager entityManager,
            final Class<T> clazz,
            final BiConsumer<EntityManager, T> logging,
            final String where) {
        final String entityName = getEntityName(entityManager, clazz);
        System.out.println("instances of " + entityName + " by Query:");

        final List<T> objs;
        if ("".length() == 10) {
            final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> criteria = builder.createQuery(clazz);
            final Root<T> root = criteria.from(clazz);
            criteria.select(root);
            objs = entityManager.createQuery(criteria).getResultList();
        } else {
            final TypedQuery<T> query =
                entityManager.createQuery("select a from `" + entityName + "` a " + where, clazz);
            objs = query.getResultList();
        }
        for (final T obj : objs) {
            logging.accept(entityManager, obj);
        }
        System.out.println();
    }

    private static void logUserRole(final EntityManager entityManager, final DbUserRole obj) {
        System.out
            .println(
                getObjEntityName(entityManager, obj) + ": " + obj.getId() + "," + obj.getName());
        System.out
            .println(
                "    users: " + obj
                    .getUsers()
                    .stream()
                    .map(DbUser::getName)
                    .collect(Collectors.joining(",")));
    }

    private static void logUser(final EntityManager entityManager, final DbUser obj) {
        System.out
            .println(
                getObjEntityName(entityManager, obj) + ": " + obj.getId() + "," + obj.getName()
                    + getDbAccountString(obj));
        System.out
            .println(
                "    roles: " + obj
                    .getUserRoles()
                    .stream()
                    .map(DbUserRole::getName)
                    .collect(Collectors.joining(",")));
    }

    private static String getDbAccountString(final DbUser obj) {
        String s;
        if (obj instanceof DbAccount) {
            s = ",IsLocalUser:" + ((DbAccount) obj).getIsLocalUser();
        } else {
            s = "";
        }
        return s;
    }

    private static String getObjEntityName(final EntityManager entityManager, final Object obj) {
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

    private static SessionFactoryBuilder makeSessionFactoryBuilder(final DataSource dataSource)
                                                                                                throws IOException {

        final ClassLoaderServiceImpl classLoaderService =
            new ClassLoaderServiceImpl(DbUser.class.getClassLoader());

        final ServiceRegistry standardRegistry =
            new StandardServiceRegistryBuilder()
                .applySetting(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, true)
                .applySetting(AvailableSettings.TRANSFORM_HBM_XML, true)
                .applySetting(
                    AvailableSettings.HBM2DDL_AUTO,
                    org.hibernate.tool.schema.Action.VALIDATE.getExternalHbm2ddlName())
                .applySetting(AvailableSettings.DATASOURCE, dataSource)
                .addService(ClassLoaderService.class, classLoaderService)
                .build();

        final MetadataSources sources = new MetadataSources(standardRegistry);
        // sources.addFile(new File("generated.hbm.xml"));
        sources.addFile(new File("generated-mapping.xml"));

        if ("".length() == 10) {
            final JaxbEntityMappings ormRoot = generateEntityMappings();
            final Origin origin = new Origin(SourceType.OTHER, "mendix-metadata");
            final Binding<JaxbEntityMappings> binding = new Binding<>(ormRoot, origin);
            sources.addXmlBinding(binding);
        }

        final MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

        final Metadata metadata = metadataBuilder.build();
        return metadata.getSessionFactoryBuilder();
    }

    private static JaxbEntityMappings generateEntityMappings() {
        // see HbmXmlTransformer
        final JaxbEntityMappings ormRoot = new JaxbEntityMappings();
        ormRoot.setDescription("generated out of current Mendix metadata");

        final JaxbPersistenceUnitMetadata metadata = new JaxbPersistenceUnitMetadata();
        // JaxbPersistenceUnitDefaults defaults = new JaxbPersistenceUnitDefaults ();
        // defaults.setDefaultAccess();
        // metadata.setPersistenceUnitDefaults(defaults );
        metadata.setXmlMappingMetadataComplete(new JaxbEmptyType());
        ormRoot.setPersistenceUnitMetadata(metadata);

        ormRoot.setPackage(DbUser.class.getPackageName());

        ormRoot.setAttributeAccessor("property");

        {
            final JaxbEntity entity = new JaxbEntity();
            entity.setName(DbUser.entityName);
            entity.setClazz(DbUser.class.getSimpleName());
            final JaxbTable table = new JaxbTable();
            table.setName("system$user");
            entity.setTable(table);

            final JaxbInheritance inheritance = new JaxbInheritance();
            inheritance.setStrategy(InheritanceType.JOINED);
            entity.setInheritance(inheritance);

            final JaxbDiscriminatorColumn discriminator = new JaxbDiscriminatorColumn();
            discriminator.setName(MxHibernateConstants.DISCRIMINATOR_COLUMN);
            discriminator.setDiscriminatorType(DiscriminatorType.STRING);
            entity.setDiscriminatorColumn(discriminator);

            final JaxbAttributes attributes = new JaxbAttributes();
            {
                final JaxbId id = new JaxbId();
                id.setName(MxHibernateConstants.ID_COLUMN);
                {
                    final JaxbColumn column = new JaxbColumn();
                    column.setName(MxHibernateConstants.ID_COLUMN);
                    id.setColumn(column);
                }
                attributes.getId().add(id);
            }

            {
                final JaxbBasic attr = new JaxbBasic();
                attr.setName(DbUser.MemberNames.Name.toString());
                {
                    final JaxbColumn column = new JaxbColumn();
                    column.setName("name");
                    attr.setColumn(column);
                }
                attributes.getBasicAttributes().add(attr);
            }

            final JaxbManyToMany manyToMany = new JaxbManyToMany();
            manyToMany.setName("userRoles");
            final JaxbJoinTable joinTable = new JaxbJoinTable();
            joinTable.setName("system$userroles");
            final JaxbJoinColumn joinColumn = new JaxbJoinColumn();
            joinColumn.setName("system$userid");
            joinTable.getJoinColumn().add(joinColumn);
            final JaxbJoinColumn inverseJoinColumn = new JaxbJoinColumn();
            inverseJoinColumn.setName("system$userroleid");
            joinTable.getInverseJoinColumn().add(inverseJoinColumn);
            manyToMany.setJoinTable(joinTable);
            attributes.getManyToManyAttributes().add(manyToMany);

            entity.setAttributes(attributes);

            ormRoot.getEntities().add(entity);
        }

        {
            final JaxbEntity entity = new JaxbEntity();
            entity.setName(DbUserRole.entityName);
            entity.setClazz(DbUserRole.class.getSimpleName());
            final JaxbTable table = new JaxbTable();
            table.setName("system$userrole");
            entity.setTable(table);

            final JaxbInheritance inheritance = new JaxbInheritance();
            inheritance.setStrategy(InheritanceType.JOINED);
            entity.setInheritance(inheritance);

            final JaxbAttributes attributes = new JaxbAttributes();
            {
                final JaxbId id = new JaxbId();
                id.setName(MxHibernateConstants.ID_COLUMN);
                {
                    final JaxbColumn column = new JaxbColumn();
                    column.setName(MxHibernateConstants.ID_COLUMN);
                    id.setColumn(column);
                }
                attributes.getId().add(id);
            }

            {
                final JaxbBasic attr = new JaxbBasic();
                attr.setName(DbUserRole.MemberNames.Name.toString());
                {
                    final JaxbColumn column = new JaxbColumn();
                    column.setName("name");
                    attr.setColumn(column);
                }
                attributes.getBasicAttributes().add(attr);
            }
            // aaa

            final JaxbManyToMany manyToMany = new JaxbManyToMany();
            manyToMany.setName("userRoles");
            final JaxbJoinTable joinTable = new JaxbJoinTable();
            joinTable.setName("system$userroles");
            final JaxbJoinColumn joinColumn = new JaxbJoinColumn();
            joinColumn.setName("system$userid");
            joinTable.getJoinColumn().add(joinColumn);
            final JaxbJoinColumn inverseJoinColumn = new JaxbJoinColumn();
            inverseJoinColumn.setName("system$userroleid");
            joinTable.getInverseJoinColumn().add(inverseJoinColumn);
            manyToMany.setJoinTable(joinTable);
            attributes.getManyToManyAttributes().add(manyToMany);

            entity.setAttributes(attributes);

            ormRoot.getEntities().add(entity);
        }

        return ormRoot;
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

}
