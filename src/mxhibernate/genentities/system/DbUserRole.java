/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbUserRole
{
    public static final java.lang.String entityName = "System.UserRole";

    public enum MemberNames
    {
        ModelGUID("ModelGUID"),
        Name("Name"),
        Description("Description"),
        grantableRoles("System.grantableRoles");

        private final java.lang.String metaName;

        MemberNames(java.lang.String s)
        {
            metaName = s;
        }

        @java.lang.Override
        public java.lang.String toString()
        {
            return metaName;
        }
    }


    private java.lang.String description;

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String val) {
        this.description = val;
    }

    private java.lang.String modelGUID;

    public java.lang.String getModelGUID() {
        return this.modelGUID;
    }

    public void setModelGUID(java.lang.String val) {
        this.modelGUID = val;
    }

    private java.lang.String name;

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String val) {
        this.name = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> userRoles;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.userRoles = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUserRole> grantableRoles;

    public java.util.Set<mxhibernate.genentities.system.DbUserRole> getGrantableRoles() {
        return this.grantableRoles;
    }

    public void setGrantableRoles(java.util.Set<mxhibernate.genentities.system.DbUserRole> val) {
        this.grantableRoles = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUserRole> grantableRoles_reverse;

    public java.util.Set<mxhibernate.genentities.system.DbUserRole> getGrantableRoles_reverse() {
        return this.grantableRoles_reverse;
    }

    public void setGrantableRoles_reverse(java.util.Set<mxhibernate.genentities.system.DbUserRole> val) {
        this.grantableRoles_reverse = val;
    }
}
