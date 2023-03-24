package mxhibernate.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name = mxhibernate.entities.DbUser.entityName)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = mxhibernate.MxHibernateConstants.SUBMETAOBJECTNAME)
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    private List<DbUserRole> userRoles;

    @ManyToMany
    @JoinTable(
            name = "System.UserRoles",
            joinColumns = @JoinColumn(
                    name = "System.UserRoles/" + mxhibernate.MxHibernateConstants.PARENT_COLUMN), // "system$userid"
            inverseJoinColumns = @JoinColumn(
                    name = "System.UserRoles/" + mxhibernate.MxHibernateConstants.CHILD_COLUMN)) // "system$userroleid"
    public List<DbUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(final List<DbUserRole> value) {
        this.userRoles = value;
    }
}
