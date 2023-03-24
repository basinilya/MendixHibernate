package mxhibernate.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;

@Entity(name = mxhibernate.entities.DbUserRole.entityName)
@Inheritance(strategy = InheritanceType.JOINED)
// @Table(name = "system$userrole")
public class DbUserRole {

    public static final java.lang.String entityName = "System.Userrole";

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

    private List<DbUser> users;

    @ManyToMany(mappedBy = "userRoles")
    public List<DbUser> getUsers() {
        return users;
    }

    public void setUsers(final List<DbUser> value) {
        this.users = value;
    }

}
