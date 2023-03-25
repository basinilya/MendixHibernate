/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbUser
{
    public static final java.lang.String entityName = "System.User";

    public enum MemberNames
    {
        Name("Name"),
        Password("Password"),
        LastLogin("LastLogin"),
        Blocked("Blocked"),
        BlockedSince("BlockedSince"),
        Active("Active"),
        FailedLogins("FailedLogins"),
        WebServiceUser("WebServiceUser"),
        IsAnonymous("IsAnonymous"),
        UserRoles("System.UserRoles"),
        User_Language("System.User_Language"),
        User_TimeZone("System.User_TimeZone");

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


    public java.lang.Boolean getActive() {
        return null;
    }

    public void setActive(java.lang.Boolean val) {
    }

    public java.lang.Boolean getBlocked() {
        return null;
    }

    public void setBlocked(java.lang.Boolean val) {
    }

    public java.util.Date getBlockedSince() {
        return null;
    }

    public void setBlockedSince(java.util.Date val) {
    }

    public java.lang.Integer getFailedLogins() {
        return null;
    }

    public void setFailedLogins(java.lang.Integer val) {
    }

    public java.lang.Boolean getIsAnonymous() {
        return null;
    }

    public void setIsAnonymous(java.lang.Boolean val) {
    }

    public java.util.Date getLastLogin() {
        return null;
    }

    public void setLastLogin(java.util.Date val) {
    }

    public java.lang.String getName() {
        return null;
    }

    public void setName(java.lang.String val) {
    }

    public java.lang.String getPassword() {
        return null;
    }

    public void setPassword(java.lang.String val) {
    }

    public java.lang.Boolean getWebServiceUser() {
        return null;
    }

    public void setWebServiceUser(java.lang.Boolean val) {
    }
}
