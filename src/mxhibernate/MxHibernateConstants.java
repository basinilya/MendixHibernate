package mxhibernate;

import java.util.Set;

public class MxHibernateConstants {

    private MxHibernateConstants() {
        //
    }

    public static final String ID_COLUMN = "id";

    public static final String DISCRIMINATOR_COLUMN = "submetaobjectname";

    public static final String ATTR_DISCRIMINATOR = "submetaobjectname";

    public static final String MODULE_SYSTEM = "System";

    public static final String MODULE_SYSTEM_PREF = MODULE_SYSTEM + ".";

    public static final String ASSOC_OWNER = "System.owner";

    public static final String ASSOC_CHANGEDBY = "System.changedBy";

    public static final String ATTR_CREATEDDATE = "createdDate";

    public static final String ATTR_CHANGEDDATE = "changedDate";

    public static final String ATTR___UUID__ = "__UUID__";

    public static final String ATTR___FILENAME__ = "__FileName__";

    public static final Set<String> SYSTEM_ASSOCS = Set.of(ASSOC_CHANGEDBY, ASSOC_OWNER);

    public static final Set<String> SYSTEM_ATTRS = Set.of(ATTR_CREATEDDATE, ATTR_CHANGEDDATE);

    public static final String ENTITY_FILEDOCUMENT = "System.FileDocument";

    public static final Set<String> FILEDOCUMENT_SYSTEM_ATTRS =
        Set.of(ATTR___UUID__, ATTR___FILENAME__);

    // rendition is parent
    public static final String PARENT_COLUMN = "parent_column";

    // edition is child
    public static final String CHILD_COLUMN = "child_column";
}
