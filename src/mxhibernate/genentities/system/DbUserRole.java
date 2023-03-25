/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbUserRole
{
    public static final java.lang.String entityName = "System.UserRole";

    public enum MemberNames
    {
        ModelGUID("ModelGUID"),
        Description("Description"),
        Name("Name"),
        grantableRoles("System.grantableRoles"),
        UserRoles("System.UserRoles");

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

}
