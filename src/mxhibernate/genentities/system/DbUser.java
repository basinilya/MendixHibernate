/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbUser
{
    public static final java.lang.String entityName = "System.User";

    public enum MemberNames
    {
        changedDate("changedDate"),
        Active("Active"),
        createdDate("createdDate"),
        LastLogin("LastLogin"),
        WebServiceUser("WebServiceUser"),
        IsAnonymous("IsAnonymous"),
        FailedLogins("FailedLogins"),
        Blocked("Blocked"),
        submetaobjectname("submetaobjectname"),
        BlockedSince("BlockedSince"),
        Name("Name"),
        Password("Password"),
        owner("System.owner"),
        User_Language("System.User_Language"),
        changedBy("System.changedBy"),
        User_TimeZone("System.User_TimeZone"),
        UserRoles("System.UserRoles"),
        WorkflowActivity_TaskActor("System.WorkflowActivity_TaskActor"),
        WorkflowUserTask_TargetUsers("System.WorkflowUserTask_TargetUsers"),
        WorkflowUserTask_Assignee("System.WorkflowUserTask_Assignee"),
        TokenInformation_User("System.TokenInformation_User"),
        Session_User("System.Session_User"),
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

}
