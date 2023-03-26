package mxhibernate;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcEntities {

    private static final Logger LOGGER = LoggerFactory.getLogger("AAAMisc");

    private static JdbcEntities INSTANCE;

    /** system attr present in all tables of a hierarchy */
    public static final String ATTR_SUBMETAOBJECTNAME = "submetaobjectname";

    // known:
    // HSQL Database Engine
    //
    private final String databaseProduct;

    public final Map<String, Entity> entitiesById = new HashMap<>();

    public final Map<String, Entity> entitiesByName = new HashMap<>();

    public final Map<String, Attribute> attributesById = new HashMap<>();

    public final Map<String, Association> assocsById = new HashMap<>();

    public final Map<String, Association> assocsByName = new HashMap<>();

    public static synchronized JdbcEntities getInstance(final Connection connection) throws Exception {
        // TODO: check version value in db
        if (INSTANCE == null) {
            INSTANCE = new JdbcEntities(connection);
        }
        return INSTANCE;
    }

    private JdbcEntities(final Connection connection) throws Exception {

        final DatabaseMetaData databaseMetaData = connection.getMetaData();
        databaseProduct = databaseMetaData.getDatabaseProductName();

        LOGGER.info("Initializing JDBC Mendix Metadata with databaseProduct: " + databaseProduct);

        String sql;
        sql = makeEntitySql();

        try (
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                final Entity entity = new Entity();
                entity.id = rs.getString("id");
                entity.entity_name = rs.getString("entity_name");
                entity.table_name = rs.getString("table_name");
                entity.superentity_id = rs.getString("superentity_id");
                entity.subentity_ids = new HashSet<>();
                entity.remote = rs.getBoolean("remote");
                entity.remote_primary_key = rs.getBoolean("remote_primary_key");

                entitiesById.put(entity.id, entity);
                entitiesByName.put(entity.entity_name, entity);
            }
        }
        entitiesByName.values().forEach(entity -> {
            if (entity.superentity_id != null) {
                entitiesById.get(entity.superentity_id).subentity_ids.add(entity.id);
            }
        });

        sql = makeAttributeSql();

        try (
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                final Attribute attribute = new Attribute();
                attribute.id = rs.getString("id");
                attribute.entity_id = rs.getString("entity_id");
                attribute.attribute_name = rs.getString("attribute_name");
                attribute.column_name = rs.getString("column_name");
                attribute.type = rs.getInt("type");
                attribute.length = rs.getInt("length");
                attribute.default_value = rs.getString("default_value");
                attribute.is_auto_number = rs.getBoolean("is_auto_number");

                attributesById.put(attribute.id, attribute);

                final Entity entity = entitiesById.get(attribute.entity_id);
                attribute.entity = entity;
                entity.attributesByName.put(attribute.attribute_name, attribute);
                entity.attributesByColumn.put(attribute.column_name, attribute);
            }
        }

        sql = makeAssociationSql();

        try (
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                final Association assoc = new Association();
                assoc.id = rs.getString("id");
                assoc.association_name = rs.getString("association_name");
                assoc.table_name = rs.getString("table_name");
                assoc.parent_entity_id = rs.getString("parent_entity_id");
                assoc.child_entity_id = rs.getString("child_entity_id");
                assoc.parent_column_name = rs.getString("parent_column_name");
                assoc.child_column_name = rs.getString("child_column_name");

                assocsById.put(assoc.id, assoc);
                assocsByName.put(assoc.association_name, assoc);

                final Entity parentEntity = entitiesById.get(assoc.parent_entity_id);
                final Entity childEntity = entitiesById.get(assoc.child_entity_id);
                assoc.parentEntity = parentEntity;
                assoc.childEntity = childEntity;
                
                parentEntity.assocWithChildByName.put(assoc.association_name, assoc);
                childEntity.assocWithParentByName.put(assoc.association_name, assoc);
            }
        }

    }

    private String makeAssociationSql() {
        return adaptForProduct("select \"id\", \"association_name\", \"table_name\", \"parent_entity_id\", \"child_entity_id\", \"parent_column_name\", \"child_column_name\" from \"mendixsystem$association\"");
    }

    private String makeAttributeSql() {
        return adaptForProduct("select \"id\", \"entity_id\", \"attribute_name\", \"column_name\", \"type\", \"length\", \"default_value\", \"is_auto_number\" from \"mendixsystem$attribute\"");
    }

    private String makeEntitySql() {
        return adaptForProduct("select \"id\", \"entity_name\", \"table_name\", \"superentity_id\", \"remote\", \"remote_primary_key\" from \"mendixsystem$entity\"");
    }

    private String adaptForProduct(final String string) {
        // TODO: this might be upper case in Oracle
        return string;
    }

    public static class Association {

        public Entity parentEntity;

        public Entity childEntity;

        public String id;

        public String association_name;

        public String table_name;

        public String parent_entity_id;

        public String child_entity_id;

        public String parent_column_name;

        public String child_column_name;
    }

    public static class Attribute {

        public String id;

        public String entity_id;

        public String attribute_name;

        public String column_name;

        public int type;

        public int length;

        public String default_value;

        public boolean is_auto_number;

        public Entity entity;
    }

    public static class Entity {

        public String id;

        public String entity_name;

        public String table_name;

        public String superentity_id;

        public Set<String> subentity_ids;

        public boolean remote;

        public boolean remote_primary_key;

        public Map<String, Attribute> attributesByName = new HashMap<>();

        public Map<String, Attribute> attributesByColumn = new HashMap<>();

        public Map<String, Association> assocWithChildByName = new HashMap<>();

        public Map<String, Association> assocWithParentByName = new HashMap<>();

    }
}
