/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbTokenInformation
{
    public static final java.lang.String entityName = "System.TokenInformation";

    public enum MemberNames
    {
        ExpiryDate("ExpiryDate"),
        UserAgent("UserAgent"),
        Token("Token"),
        TokenInformation_User("System.TokenInformation_User");

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
