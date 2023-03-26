package mxhibernate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
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
import org.hibernate.boot.jaxb.mapping.JaxbTransient;
import org.hibernate.boot.jaxb.spi.Binding;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.AccessType;
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
import mxhibernate.JdbcEntities.Association;
import mxhibernate.JdbcEntities.Attribute;
import mxhibernate.JdbcEntities.Entity;
import mxhibernate.genentities.administration.DbAccount;
import mxhibernate.genentities.system.DbUser;
import mxhibernate.genentities.system.DbUserRole;

/**
 * Generate Hibernate mapping XML out of Mendix database and POJOs generated with
 * {@link GenerateMendixJdbcProxies}.
 */
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

    private static void dumpconf(final JaxbEntityMappings obj, final OutputStream os)
                                                                                      throws JAXBException,
                                                                                          PropertyException {
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, os);
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
        final List<Long> userIds;
        final JdbcEntities jdbcEntities;
        try (Connection conn = dataSource.getConnection();) {
            jdbcEntities = JdbcEntities.getInstance(conn);
            userIds = logUsersWithPureJdbc(conn);
            logAccountsWithPureJdbc(conn);
            logRolesWithPureJdbc(conn);
        }
        logUsersWithHibernate(dataSource, userIds, jdbcEntities);
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

    private static void logUsersWithHibernate(
            final DataSource dataSource,
            final List<Long> userIds,
            final JdbcEntities jdbcEntities) throws Exception {
        try (
            final SessionFactory sessionFactory =
                makeSessionFactoryBuilder(dataSource, jdbcEntities);) {
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
                    .getUserRoles()
                    .stream()
                    .map(DbUser::getName)
                    .collect(Collectors.joining(",")));
        System.out
            .println(
                "    grantableRoles: " + obj
                    .getGrantableRoles()
                    .stream()
                    .map(DbUserRole::getName)
                    .collect(Collectors.joining(",")));
        System.out
            .println(
                "    grantableRoles reverse: " + obj
                    .getGrantableRoles_reverse()
                    .stream()
                    .map(DbUserRole::getName)
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

    private static SessionFactory makeSessionFactoryBuilder(
            final DataSource dataSource,
            final JdbcEntities jdbcEntities) throws Exception {

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
        // sources.addFile(new File("generated-mapping.xml"));
        addGeneratedMappings(sources, jdbcEntities);

        final MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
        final Metadata metadata = metadataBuilder.build();
        final SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        validateAttributeResolvation(sources, sessionFactory);

        return sessionFactory;
    }

    private static void validateAttributeResolvation(
            final MetadataSources sources,
            final SessionFactory sessionFactory) throws IllegalArgumentException {
        {
            final Map<String, EntityType<?>> entitiesByName =
                sessionFactory
                    .getMetamodel()
                    .getEntities()
                    .stream()
                    .collect(Collectors.toMap(EntityType::getName, Function.identity()));
            sources
                .getXmlBindings()
                .stream()
                .map(binding -> (JaxbEntityMappings) binding.getRoot())
                .forEach(ormRoot -> {
                    ormRoot.getEntities().forEach(xmlEntity -> {
                        final String entityName = xmlEntity.getName();
                        final EntityType<?> entity = entitiesByName.get(entityName);
                        xmlEntity.getAttributes().getBasicAttributes().forEach(xmlAttribute -> {
                            final String xmlAttributeName = xmlAttribute.getName();
                            Objects.requireNonNull(entity.getAttribute(xmlAttributeName));
                        });
                    });
                });
        }
    }

    private static void addGeneratedMappings(
            final MetadataSources sources,
            final JdbcEntities jdbcEntities) throws Exception {
        final JaxbEntityMappings ormRoot = generateEntityMappings(jdbcEntities);
        final Origin origin = new Origin(SourceType.OTHER, "mendix-metadata");
        final Binding<JaxbEntityMappings> binding = new Binding<>(ormRoot, origin);
        sources.addXmlBinding(binding);
    }

    private static JaxbEntityMappings generateEntityMappings(final JdbcEntities jdbcEntities)
                                                                                              throws Exception {
        // see HbmXmlTransformer
        final JaxbEntityMappings ormRoot = new JaxbEntityMappings();
        ormRoot.setDescription("generated out of current Mendix metadata");

        final JaxbPersistenceUnitMetadata metadata = new JaxbPersistenceUnitMetadata();
        metadata.setXmlMappingMetadataComplete(new JaxbEmptyType());
        ormRoot.setPersistenceUnitMetadata(metadata);

        ormRoot.setAccess(AccessType.PROPERTY);

        final Collection<Entity> mxEntities = jdbcEntities.entitiesByName.values();

        final Set<String> blackListEntities = new HashSet<>();
        for (final Entity mxEntity : mxEntities) {
            final String entityName = mxEntity.entity_name;
            final String newClassName =
                GenerateMendixJdbcProxies.entityNameToNewClassName(entityName);
            try {
                Class.forName(newClassName);
            } catch (final ClassNotFoundException e) {
                if (!entityName.startsWith(MxMetaModelConstants.MODULE_SYSTEM_PREF)) {
                    throw e;
                }
                // entities like System.WorkflowVersion have no proxies
                blackListEntities.add(entityName);
            }
        }

        for (final Entity mxEntity : mxEntities) {
            final String entityName = mxEntity.entity_name;

            if (blackListEntities.contains(entityName)) {
                continue;
            }

            final String beanClassName =
                GenerateMendixJdbcProxies.entityNameToNewClassName(entityName);

            final JaxbEntity entity = new JaxbEntity();
            entity.setName(entityName);
            entity.setClazz(beanClassName);
            final JaxbTable table = new JaxbTable();
            table.setName(mxEntity.table_name);
            entity.setTable(table);

            final JaxbAttributes attributes = new JaxbAttributes();

            if (mxEntity.superentity_id == null) {
                final JaxbInheritance inheritance = new JaxbInheritance();
                inheritance.setStrategy(InheritanceType.JOINED);
                entity.setInheritance(inheritance);

                if (!mxEntity.subentity_ids.isEmpty()) {
                    final JaxbDiscriminatorColumn discriminator = new JaxbDiscriminatorColumn();
                    discriminator.setName(MxMetaModelConstants.DISCRIMINATOR_COLUMN);
                    discriminator.setDiscriminatorType(DiscriminatorType.STRING);
                    entity.setDiscriminatorColumn(discriminator);
                }

                final JaxbId id = new JaxbId();
                id.setName(MxMetaModelConstants.ID_COLUMN);
                {
                    final JaxbColumn column = new JaxbColumn();
                    column.setName(MxMetaModelConstants.ID_COLUMN);
                    id.setColumn(column);
                }
                attributes.getId().add(id);
            }

            final Class<?> beanClass = Class.forName(beanClassName);
            final BeanInfo info = Introspector.getBeanInfo(beanClass);
            final Set<String> remainingDeclaredJavaProps =
                Arrays
                    .stream(info.getPropertyDescriptors())
                    .filter(pd -> isDeclared(pd, beanClass))
                    .map(PropertyDescriptor::getName)
                    .filter(s -> !MxMetaModelConstants.ID_COLUMN.equals(s))
                    .collect(Collectors.toCollection(() -> new HashSet<>()));

            for (final Attribute mxAttribute : mxEntity.attributesByName.values()) {
                final String mxAttributeName = mxAttribute.attribute_name;
                final String javaPropName = Introspector.decapitalize(mxAttributeName);

                if (MxMetaModelConstants.ATTR_DISCRIMINATOR.equals(mxAttributeName)) {
                    continue;
                }

                if (!remainingDeclaredJavaProps.remove(javaPropName)) {
                    if (!entityName.startsWith(MxMetaModelConstants.MODULE_SYSTEM_PREF)) {
                        throw new RuntimeException(
                            "java property not found: " + beanClassName + "." + javaPropName);
                    }
                    // attributes like System.Workflow/ObjectId have no java property
                    continue;
                }

                if (MxMetaModelConstants.ATTR_DISCRIMINATOR.equals(mxAttributeName)
                    || MxMetaModelConstants.SYSTEM_ATTRS.contains(mxAttributeName)
                    || MxMetaModelConstants.ENTITY_FILEDOCUMENT.equals(entityName)
                        && MxMetaModelConstants.FILEDOCUMENT_SYSTEM_ATTRS
                            .contains(mxAttributeName)) {
                    // continue;
                }

                final JaxbBasic attr = new JaxbBasic();
                attr.setName(javaPropName);
                final JaxbColumn column = new JaxbColumn();
                column.setName(mxAttribute.column_name);
                attr.setColumn(column);
                attributes.getBasicAttributes().add(attr);
            }

            for (final Association mxAssoc : mxEntity.assocWithChildByName.values()) {
                if (MxMetaModelConstants.SYSTEM_ASSOCS.contains(mxAssoc.association_name)) {
                    continue;
                }
                if (blackListEntities.contains(mxAssoc.childEntity.entity_name)) {
                    continue;
                }
                final String javaPropName =
                    GenerateMendixJdbcProxies.assocToPropName(mxAssoc.association_name);
                remainingDeclaredJavaProps.remove(javaPropName);

                final JaxbManyToMany manyToMany = new JaxbManyToMany();
                manyToMany.setName(javaPropName);
                final JaxbJoinTable joinTable = new JaxbJoinTable();
                joinTable.setName(mxAssoc.table_name);
                final JaxbJoinColumn joinColumn = new JaxbJoinColumn();
                joinColumn.setName(mxAssoc.parent_column_name);
                joinTable.getJoinColumn().add(joinColumn);
                final JaxbJoinColumn inverseJoinColumn = new JaxbJoinColumn();
                inverseJoinColumn.setName(mxAssoc.child_column_name);
                joinTable.getInverseJoinColumn().add(inverseJoinColumn);
                manyToMany.setJoinTable(joinTable);
                attributes.getManyToManyAttributes().add(manyToMany);
            }
            for (final Association mxAssoc : mxEntity.assocWithParentByName.values()) {
                if (MxMetaModelConstants.SYSTEM_ASSOCS.contains(mxAssoc.association_name)) {
                    // User has owner
                    continue;
                }
                if (blackListEntities.contains(mxAssoc.parentEntity.entity_name)) {
                    continue;
                }
                final JaxbManyToMany manyToMany = new JaxbManyToMany();
                final String ownerPropName =
                    GenerateMendixJdbcProxies.assocToPropName(mxAssoc.association_name);

                final String propName =
                    mxEntity.assocWithChildByName.containsKey(mxAssoc.association_name)
                        ? ownerPropName + GenerateMendixJdbcProxies.SUFFIX_REVERSE
                        : ownerPropName;
                remainingDeclaredJavaProps.remove(propName);

                manyToMany.setName(propName);
                manyToMany.setMappedBy(ownerPropName);
                attributes.getManyToManyAttributes().add(manyToMany);
            }

            for (final String remainingDeclaredJavaProp : remainingDeclaredJavaProps) {
                // calculated reference to non-persistable
                final JaxbTransient transient1 = new JaxbTransient();
                transient1.setName(remainingDeclaredJavaProp);
                attributes.getTransients().add(transient1);
            }

            entity.setAttributes(attributes);

            ormRoot.getEntities().add(entity);

        }

        dumpconf(ormRoot, new FileOutputStream("generated-mapping-2.xml"));
        return ormRoot;
    }

    private static boolean isDeclared(final PropertyDescriptor pd, final Class<?> beanClass) {
        final Method readMethod = pd.getReadMethod();
        final Method anyMethod = readMethod == null ? pd.getWriteMethod() : readMethod;
        return beanClass.equals(anyMethod.getDeclaringClass());
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
