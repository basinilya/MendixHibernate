package mxhibernate.entities;


public class DbAccount extends DbUser {

    public static final java.lang.String entityName = "Administration.Account";

    public enum MemberNames {

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

        MemberNames(final java.lang.String s) {
            metaName = s;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return metaName;
        }
    }

    private Boolean isLocalUser1;

    public Boolean getIsLocalUser() {
        return isLocalUser1;
    }

    public void setIsLocalUser(final Boolean isLocalUser) {
        this.isLocalUser1 = isLocalUser;
    }
}
