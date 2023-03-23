package mxhibernate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = mxhibernate.entities.DbAccount.entityName)
@Table(name = "administration$account")
public class DbAccount extends DbUser {

    public static final java.lang.String entityName = "Administration.Account";

}
