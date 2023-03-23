package snippet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity(name = snippet.entities.DbUser.entityName)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "system$user")
// @DiscriminatorFormula("'as ")
@DiscriminatorColumn(name = "submetaobjectname")
public class DbUser {

    public static final java.lang.String entityName = "System.User";

    private long id;

    private String name;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(final String aaa) {
        this.name = aaa;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}
