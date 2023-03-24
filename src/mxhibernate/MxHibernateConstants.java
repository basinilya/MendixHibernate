package mxhibernate;


public class MxHibernateConstants {

    private MxHibernateConstants() {
        //
    }

    public static final String ID_COLUMN = "id";

    public static final String DISCRIMINATOR_COLUMN = "submetaobjectname";

    // rendition is parent
    public static final String PARENT_COLUMN = "parent_column";

    // edition is child
    public static final String CHILD_COLUMN = "child_column";
}
