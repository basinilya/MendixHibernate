/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.myfirstmodule;

public class DbMyParentEx extends mxhibernate.genentities.myfirstmodule.DbMyParent
{
    public static final java.lang.String entityName = "MyFirstModule.MyParentEx";

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
}
