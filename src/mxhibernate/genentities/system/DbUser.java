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

    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long val) {
        this.id = val;
    }

    private java.lang.Boolean active;

    public java.lang.Boolean getActive() {
        return this.active;
    }

    public void setActive(java.lang.Boolean val) {
        this.active = val;
    }

    private java.lang.Boolean blocked;

    public java.lang.Boolean getBlocked() {
        return this.blocked;
    }

    public void setBlocked(java.lang.Boolean val) {
        this.blocked = val;
    }

    private java.util.Date blockedSince;

    public java.util.Date getBlockedSince() {
        return this.blockedSince;
    }

    public void setBlockedSince(java.util.Date val) {
        this.blockedSince = val;
    }

    private java.lang.Integer failedLogins;

    public java.lang.Integer getFailedLogins() {
        return this.failedLogins;
    }

    public void setFailedLogins(java.lang.Integer val) {
        this.failedLogins = val;
    }

    private java.lang.Boolean isAnonymous;

    public java.lang.Boolean getIsAnonymous() {
        return this.isAnonymous;
    }

    public void setIsAnonymous(java.lang.Boolean val) {
        this.isAnonymous = val;
    }

    private java.util.Date lastLogin;

    public java.util.Date getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(java.util.Date val) {
        this.lastLogin = val;
    }

    private java.lang.String name;

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String val) {
        this.name = val;
    }

    private java.lang.String password;

    public java.lang.String getPassword() {
        return this.password;
    }

    public void setPassword(java.lang.String val) {
        this.password = val;
    }

    private java.lang.Boolean webServiceUser;

    public java.lang.Boolean getWebServiceUser() {
        return this.webServiceUser;
    }

    public void setWebServiceUser(java.lang.Boolean val) {
        this.webServiceUser = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUserRole> userRoles;

    public java.util.Set<mxhibernate.genentities.system.DbUserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(java.util.Set<mxhibernate.genentities.system.DbUserRole> val) {
        this.userRoles = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUserReportInfo> userReportInfo_User;

    public java.util.Set<mxhibernate.genentities.system.DbUserReportInfo> getUserReportInfo_User() {
        return this.userReportInfo_User;
    }

    public void setUserReportInfo_User(java.util.Set<mxhibernate.genentities.system.DbUserReportInfo> val) {
        this.userReportInfo_User = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbTokenInformation> tokenInformation_User;

    public java.util.Set<mxhibernate.genentities.system.DbTokenInformation> getTokenInformation_User() {
        return this.tokenInformation_User;
    }

    public void setTokenInformation_User(java.util.Set<mxhibernate.genentities.system.DbTokenInformation> val) {
        this.tokenInformation_User = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbTimeZone> user_TimeZone;

    public java.util.Set<mxhibernate.genentities.system.DbTimeZone> getUser_TimeZone() {
        return this.user_TimeZone;
    }

    public void setUser_TimeZone(java.util.Set<mxhibernate.genentities.system.DbTimeZone> val) {
        this.user_TimeZone = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> workflowUserTask_TargetUsers;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_TargetUsers() {
        return this.workflowUserTask_TargetUsers;
    }

    public void setWorkflowUserTask_TargetUsers(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
        this.workflowUserTask_TargetUsers = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> workflowUserTask_Assignee;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_Assignee() {
        return this.workflowUserTask_Assignee;
    }

    public void setWorkflowUserTask_Assignee(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
        this.workflowUserTask_Assignee = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbLanguage> user_Language;

    public java.util.Set<mxhibernate.genentities.system.DbLanguage> getUser_Language() {
        return this.user_Language;
    }

    public void setUser_Language(java.util.Set<mxhibernate.genentities.system.DbLanguage> val) {
        this.user_Language = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbSession> session_User;

    public java.util.Set<mxhibernate.genentities.system.DbSession> getSession_User() {
        return this.session_User;
    }

    public void setSession_User(java.util.Set<mxhibernate.genentities.system.DbSession> val) {
        this.session_User = val;
    }
}
