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


    public java.lang.String getAttribute() {
        return null;
    }

    public void setAttribute(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getMyParent_MyChild() {
        return null;
    }

    public void setMyParent_MyChild(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
    }
}
