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


    private java.lang.String key;

    public java.lang.String getKey() {
        return this.key;
    }

    public void setKey(java.lang.String val) {
        this.key = val;
    }

    private java.lang.String value;

    public java.lang.String getValue() {
        return this.value;
    }

    public void setValue(java.lang.String val) {
        this.value = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbHttpMessage> httpHeaders;

    public java.util.Set<mxhibernate.genentities.system.DbHttpMessage> getHttpHeaders() {
        return this.httpHeaders;
    }

    public void setHttpHeaders(java.util.Set<mxhibernate.genentities.system.DbHttpMessage> val) {
        this.httpHeaders = val;
    }
}
