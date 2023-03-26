/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbHttpMessage
{
    public static final java.lang.String entityName = "System.HttpMessage";

    public enum MemberNames
    {
        HttpVersion("HttpVersion"),
        Content("Content");

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


    public java.lang.String getContent() {
        return null;
    }

    public void setContent(java.lang.String val) {
    }

    public java.lang.String getHttpVersion() {
        return null;
    }

    public void setHttpVersion(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbHttpHeader> getHttpHeaders() {
        return null;
    }

    public void setHttpHeaders(java.util.Set<mxhibernate.genentities.system.DbHttpHeader> val) {
    }
}
