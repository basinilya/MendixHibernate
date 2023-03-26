/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbSession
{
    public static final java.lang.String entityName = "System.Session";

    public enum MemberNames
    {
        SessionId("SessionId"),
        CSRFToken("CSRFToken"),
        LastActive("LastActive"),
        Session_User("System.Session_User");

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


    private java.lang.String CSRFToken;

    public java.lang.String getCSRFToken() {
        return this.CSRFToken;
    }

    public void setCSRFToken(java.lang.String val) {
        this.CSRFToken = val;
    }

    private java.util.Date lastActive;

    public java.util.Date getLastActive() {
        return this.lastActive;
    }

    public void setLastActive(java.util.Date val) {
        this.lastActive = val;
    }

    private java.lang.String sessionId;

    public java.lang.String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(java.lang.String val) {
        this.sessionId = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> session_User;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getSession_User() {
        return this.session_User;
    }

    public void setSession_User(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.session_User = val;
    }
}
