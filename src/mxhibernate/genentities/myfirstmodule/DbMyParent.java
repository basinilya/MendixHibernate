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
}
