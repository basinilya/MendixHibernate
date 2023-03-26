/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.myfirstmodule;

public class DbMyParent
{
    public static final java.lang.String entityName = "MyFirstModule.MyParent";

    public enum MemberNames
    {
        Attribute("Attribute"),
        MyParent_MyChild("MyFirstModule.MyParent_MyChild"),
        One_One("MyFirstModule.One_One"),
        Many_Many("MyFirstModule.Many_Many");

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

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyChild> getMyParent_MyChild() {
        return null;
    }

    public void setMyParent_MyChild(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyChild> val) {
    }

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getMany_Many() {
        return null;
    }

    public void setMany_Many(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
    }

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getMany_Many_reverse() {
        return null;
    }

    public void setMany_Many_reverse(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
    }

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getOne_One() {
        return null;
    }

    public void setOne_One(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
    }

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getOne_One_reverse() {
        return null;
    }

    public void setOne_One_reverse(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
    }
}
