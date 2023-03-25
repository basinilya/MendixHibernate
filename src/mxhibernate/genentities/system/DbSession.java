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


    public java.lang.String getCSRFToken() {
        return null;
    }

    public void setCSRFToken(java.lang.String val) {
    }

    public java.util.Date getLastActive() {
        return null;
    }

    public void setLastActive(java.util.Date val) {
    }

    public java.lang.String getSessionId() {
        return null;
    }

    public void setSessionId(java.lang.String val) {
    }
}
