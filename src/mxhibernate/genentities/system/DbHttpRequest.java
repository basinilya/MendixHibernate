/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbHttpRequest extends mxhibernate.genentities.system.DbHttpMessage
{
    public static final java.lang.String entityName = "System.HttpRequest";

    public enum MemberNames
    {
        Uri("Uri"),
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


    public java.lang.String getUri() {
        return null;
    }

    public void setUri(java.lang.String val) {
    }
}
