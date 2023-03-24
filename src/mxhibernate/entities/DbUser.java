package mxhibernate.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name = DbUser.entityName)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = mxhibernate.MxHibernateConstants.DISCRIMINATOR_COLUMN)
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

    @Column(name = DbUser.entityName + "/Name")
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

    @ManyToMany
    @JoinTable(
            name = "System.UserRoles",
            joinColumns = @JoinColumn(
                    name = "System.UserRoles/" + mxhibernate.MxHibernateConstants.PARENT_COLUMN),
            inverseJoinColumns = @JoinColumn(
                    name = "System.UserRoles/" + mxhibernate.MxHibernateConstants.CHILD_COLUMN))
    public Set<DbUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(final Set<DbUserRole> value) {
        this.userRoles = value;
    }
}
