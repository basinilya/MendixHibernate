/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.administration;

public class DbAccount extends mxhibernate.genentities.system.DbUser
{
    public static final java.lang.String entityName = "Administration.Account";

    public enum MemberNames
    {
        FullName("FullName"),
        Email("Email"),
        IsLocalUser("IsLocalUser"),
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


    public java.lang.String getEmail() {
        return null;
    }

    public void setEmail(java.lang.String val) {
    }

    public java.lang.String getFullName() {
        return null;
    }

    public void setFullName(java.lang.String val) {
    }

    public java.lang.Boolean getIsLocalUser() {
        return null;
    }

    public void setIsLocalUser(java.lang.Boolean val) {
    }

    public java.util.Set<mxhibernate.genentities.administration.DbAccountPasswordData> getAccountPasswordData_Account() {
        return null;
    }

    public void setAccountPasswordData_Account(java.util.Set<mxhibernate.genentities.administration.DbAccountPasswordData> val) {
    }
}
