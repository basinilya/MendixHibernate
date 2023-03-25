/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.administration;

public class DbAccount extends mxhibernate.genentities.system.DbUser
{
    public static final java.lang.String entityName = "Administration.Account";

    public enum MemberNames
    {
        Email("Email"),
        FullName("FullName"),
        IsLocalUser("IsLocalUser");

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
