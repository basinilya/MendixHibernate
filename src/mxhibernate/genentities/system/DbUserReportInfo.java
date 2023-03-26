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

    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long val) {
        this.id = val;
    }

    private java.lang.String hash;

    public java.lang.String getHash() {
        return this.hash;
    }

    public void setHash(java.lang.String val) {
        this.hash = val;
    }

    private java.lang.String userType;

    public java.lang.String getUserType() {
        return this.userType;
    }

    public void setUserType(java.lang.String val) {
        this.userType = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> userReportInfo_User;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getUserReportInfo_User() {
        return this.userReportInfo_User;
    }

    public void setUserReportInfo_User(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.userReportInfo_User = val;
    }
}
