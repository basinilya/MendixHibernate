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

    private java.util.Date expiryDate;

    public java.util.Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(java.util.Date val) {
        this.expiryDate = val;
    }

    private java.lang.String token;

    public java.lang.String getToken() {
        return this.token;
    }

    public void setToken(java.lang.String val) {
        this.token = val;
    }

    private java.lang.String userAgent;

    public java.lang.String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(java.lang.String val) {
        this.userAgent = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> tokenInformation_User;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getTokenInformation_User() {
        return this.tokenInformation_User;
    }

    public void setTokenInformation_User(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.tokenInformation_User = val;
    }
}
