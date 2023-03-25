/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbError
{
    public static final java.lang.String entityName = "System.Error";

    public enum MemberNames
    {
        ErrorType("ErrorType"),
        Message("Message"),
        Stacktrace("Stacktrace");

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


    public java.lang.String getErrorType() {
        return null;
    }

    public void setErrorType(java.lang.String val) {
    }

    public java.lang.String getMessage() {
        return null;
    }

    public void setMessage(java.lang.String val) {
    }

    public java.lang.String getStacktrace() {
        return null;
    }

    public void setStacktrace(java.lang.String val) {
    }
}
