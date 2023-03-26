/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.myfirstmodule;

public class DbMyChild
{
    public static final java.lang.String entityName = "MyFirstModule.MyChild";

    public enum MemberNames
    {
        Attribute("Attribute"),
        MyParent_MyChild("MyFirstModule.MyParent_MyChild");

        private final java.lang.String metaName;

        MemberNames(java.lang.String s)
        {
            metaName = s;
        }

        @java.lang.Override
        public java.lang.String toString()
        {
            return metaName;
        }
    }

    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long val) {
        this.id = val;
    }

    private java.lang.String attribute;

    public java.lang.String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(java.lang.String val) {
        this.attribute = val;
    }

    private java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> myParent_MyChild;

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getMyParent_MyChild() {
        return this.myParent_MyChild;
    }

    public void setMyParent_MyChild(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
        this.myParent_MyChild = val;
    }
}
