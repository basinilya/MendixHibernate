package mxhibernate.entities;

import java.util.Set;

public class DbUserRole {

    public static final java.lang.String entityName = "System.Userrole";

    public enum MemberNames {

            ModelGUID("ModelGUID"),
            Name("Name"),
            Description("Description"),
            grantableRoles("System.grantableRoles");

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

    private Set<DbUser> users;

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

    public Set<DbUser> getUsers() {
        return users;
    }

    public void setUsers(final Set<DbUser> value) {
        this.users = value;
    }

}
