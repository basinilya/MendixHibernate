package mxhibernate.entities;

import java.util.Set;

public class DbUser {

    public static final java.lang.String entityName = "System.User";

    public enum MemberNames {

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

    private long id;

    private String name;

    private Set<DbUserRole> userRoles;

    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Set<DbUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(final Set<DbUserRole> value) {
        this.userRoles = value;
    }
}
