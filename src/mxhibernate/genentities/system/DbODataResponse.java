/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbODataResponse
{
    public static final java.lang.String entityName = "System.ODataResponse";

    public enum MemberNames
    {
        Count("Count");

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


    private java.lang.Long count;

    public java.lang.Long getCount() {
        return this.count;
    }

    public void setCount(java.lang.Long val) {
        this.count = val;
    }
}
