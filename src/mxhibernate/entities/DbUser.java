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
// @Table(name = "system$user")
@DiscriminatorColumn(name = "submetaobjectname")
public class DbUser {

    public static final java.lang.String entityName = "System.User";

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
    // @OrderColumn(name = "_DUMMYORDERCOL")
    // @MapKeyColumn(name = "_DUMMYKEYCOL")
    // @Column(name = "_DUMMYELEMENTCOL")
    @JoinTable(
            name = "system$userroles",
            joinColumns = @JoinColumn(name = "system$userid"),
            inverseJoinColumns = @JoinColumn(name = "system$userroleid"))
    public List<DbUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(final List<DbUserRole> value) {
        this.userRoles = value;
    }
}
