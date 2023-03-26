/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbHttpHeader
{
    public static final java.lang.String entityName = "System.HttpHeader";

    public enum MemberNames
    {
        Key("Key"),
        Value("Value"),
        HttpHeaders("System.HttpHeaders");

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


    public java.lang.String getKey() {
        return null;
    }

    public void setKey(java.lang.String val) {
    }

    public java.lang.String getValue() {
        return null;
    }

    public void setValue(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbHttpMessage> getHttpHeaders() {
        return null;
    }

    public void setHttpHeaders(java.util.Set<mxhibernate.genentities.system.DbHttpMessage> val) {
    }
}
