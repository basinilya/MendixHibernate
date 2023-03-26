/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbHttpResponse extends mxhibernate.genentities.system.DbHttpMessage
{
    public static final java.lang.String entityName = "System.HttpResponse";

    public enum MemberNames
    {
        StatusCode("StatusCode"),
        ReasonPhrase("ReasonPhrase"),
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

    private java.lang.String reasonPhrase;

    public java.lang.String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public void setReasonPhrase(java.lang.String val) {
        this.reasonPhrase = val;
    }

    private java.lang.Integer statusCode;

    public java.lang.Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(java.lang.Integer val) {
        this.statusCode = val;
    }
}
