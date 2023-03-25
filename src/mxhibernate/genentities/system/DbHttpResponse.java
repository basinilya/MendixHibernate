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


    public java.lang.String getReasonPhrase() {
        return null;
    }

    public void setReasonPhrase(java.lang.String val) {
    }

    public java.lang.Integer getStatusCode() {
        return null;
    }

    public void setStatusCode(java.lang.Integer val) {
    }
}
