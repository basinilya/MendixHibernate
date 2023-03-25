/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbTokenInformation
{
    public static final java.lang.String entityName = "System.TokenInformation";

    public enum MemberNames
    {
        Token("Token"),
        ExpiryDate("ExpiryDate"),
        UserAgent("UserAgent"),
        TokenInformation_User("System.TokenInformation_User");

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


    public java.util.Date getExpiryDate() {
        return null;
    }

    public void setExpiryDate(java.util.Date val) {
    }

    public java.lang.String getToken() {
        return null;
    }

    public void setToken(java.lang.String val) {
    }

    public java.lang.String getUserAgent() {
        return null;
    }

    public void setUserAgent(java.lang.String val) {
    }
}
