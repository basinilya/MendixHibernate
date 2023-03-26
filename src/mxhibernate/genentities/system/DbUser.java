/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbUser
{
    public static final java.lang.String entityName = "System.User";

    public enum MemberNames
    {
        Name("Name"),
        Password("Password"),
        LastLogin("LastLogin"),
        Blocked("Blocked"),
        BlockedSince("BlockedSince"),
        Active("Active"),
        FailedLogins("FailedLogins"),
        WebServiceUser("WebServiceUser"),
        IsAnonymous("IsAnonymous"),
        UserRoles("System.UserRoles"),
        User_Language("System.User_Language"),
        User_TimeZone("System.User_TimeZone");

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


    public java.lang.Boolean getActive() {
        return null;
    }

    public void setActive(java.lang.Boolean val) {
    }

    public java.lang.Boolean getBlocked() {
        return null;
    }

    public void setBlocked(java.lang.Boolean val) {
    }

    public java.util.Date getBlockedSince() {
        return null;
    }

    public void setBlockedSince(java.util.Date val) {
    }

    public java.lang.Integer getFailedLogins() {
        return null;
    }

    public void setFailedLogins(java.lang.Integer val) {
    }

    public java.lang.Boolean getIsAnonymous() {
        return null;
    }

    public void setIsAnonymous(java.lang.Boolean val) {
    }

    public java.util.Date getLastLogin() {
        return null;
    }

    public void setLastLogin(java.util.Date val) {
    }

    public java.lang.String getName() {
        return null;
    }

    public void setName(java.lang.String val) {
    }

    public java.lang.String getPassword() {
        return null;
    }

    public void setPassword(java.lang.String val) {
    }

    public java.lang.Boolean getWebServiceUser() {
        return null;
    }

    public void setWebServiceUser(java.lang.Boolean val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbUserRole> getUserRoles() {
        return null;
    }

    public void setUserRoles(java.util.Set<mxhibernate.genentities.system.DbUserRole> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbUserReportInfo> getUserReportInfo_User() {
        return null;
    }

    public void setUserReportInfo_User(java.util.Set<mxhibernate.genentities.system.DbUserReportInfo> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbTokenInformation> getTokenInformation_User() {
        return null;
    }

    public void setTokenInformation_User(java.util.Set<mxhibernate.genentities.system.DbTokenInformation> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbTimeZone> getUser_TimeZone() {
        return null;
    }

    public void setUser_TimeZone(java.util.Set<mxhibernate.genentities.system.DbTimeZone> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_TargetUsers() {
        return null;
    }

    public void setWorkflowUserTask_TargetUsers(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_Assignee() {
        return null;
    }

    public void setWorkflowUserTask_Assignee(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbLanguage> getUser_Language() {
        return null;
    }

    public void setUser_Language(java.util.Set<mxhibernate.genentities.system.DbLanguage> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbSession> getSession_User() {
        return null;
    }

    public void setSession_User(java.util.Set<mxhibernate.genentities.system.DbSession> val) {
    }
}
