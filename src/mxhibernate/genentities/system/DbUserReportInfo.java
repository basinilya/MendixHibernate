/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbUserReportInfo
{
    public static final java.lang.String entityName = "System.UserReportInfo";

    public enum MemberNames
    {
        UserType("UserType"),
        Hash("Hash"),
        UserReportInfo_User("System.UserReportInfo_User");

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


    public java.lang.String getHash() {
        return null;
    }

    public void setHash(java.lang.String val) {
    }

    public mxhibernate.genentities.system.DbUserType getUserType() {
        return null;
    }

    public void setUserType(mxhibernate.genentities.system.DbUserType val) {
    }
}
