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


    private java.lang.String content;

    public java.lang.String getContent() {
        return this.content;
    }

    public void setContent(java.lang.String val) {
        this.content = val;
    }

    private java.lang.String httpVersion;

    public java.lang.String getHttpVersion() {
        return this.httpVersion;
    }

    public void setHttpVersion(java.lang.String val) {
        this.httpVersion = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbHttpHeader> httpHeaders;

    public java.util.Set<mxhibernate.genentities.system.DbHttpHeader> getHttpHeaders() {
        return this.httpHeaders;
    }

    public void setHttpHeaders(java.util.Set<mxhibernate.genentities.system.DbHttpHeader> val) {
        this.httpHeaders = val;
    }
}
