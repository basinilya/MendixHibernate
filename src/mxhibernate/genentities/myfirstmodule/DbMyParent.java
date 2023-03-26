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

    private java.lang.String attribute;

    public java.lang.String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(java.lang.String val) {
        this.attribute = val;
    }

    private java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyChild> myParent_MyChild;

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyChild> getMyParent_MyChild() {
        return this.myParent_MyChild;
    }

    public void setMyParent_MyChild(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyChild> val) {
        this.myParent_MyChild = val;
    }

    private java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> many_Many;

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getMany_Many() {
        return this.many_Many;
    }

    public void setMany_Many(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
        this.many_Many = val;
    }

    private java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> many_Many_reverse;

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getMany_Many_reverse() {
        return this.many_Many_reverse;
    }

    public void setMany_Many_reverse(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
        this.many_Many_reverse = val;
    }

    private java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> one_One;

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getOne_One() {
        return this.one_One;
    }

    public void setOne_One(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
        this.one_One = val;
    }

    private java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> one_One_reverse;

    public java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> getOne_One_reverse() {
        return this.one_One_reverse;
    }

    public void setOne_One_reverse(java.util.Set<mxhibernate.genentities.myfirstmodule.DbMyParent> val) {
        this.one_One_reverse = val;
    }
}
