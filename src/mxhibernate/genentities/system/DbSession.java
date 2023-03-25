/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbSession
{
    public static final java.lang.String entityName = "System.Session";

    public enum MemberNames
    {
        LastActionExecution("LastActionExecution"),
        ReadOnlyHashKey("ReadOnlyHashKey"),
        createdDate("createdDate"),
        CSRFToken("CSRFToken"),
        LastActive("LastActive"),
        LongLived("LongLived"),
        SessionId("SessionId"),
        Session_User("System.Session_User"),
        ChangeHash_Session("System.ChangeHash_Session"),
        BackgroundJob_Session("System.BackgroundJob_Session");

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
