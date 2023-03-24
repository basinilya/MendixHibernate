package mxhibernate.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;

@Entity(name = DbUserRole.entityName)
@Inheritance(strategy = InheritanceType.JOINED)
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

    private List<DbUser> users;

    @Column(name = DbUserRole.entityName + "/Name")
    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "userRoles")
    public List<DbUser> getUsers() {
        return users;
    }

    public void setUsers(final List<DbUser> value) {
        this.users = value;
    }

}
