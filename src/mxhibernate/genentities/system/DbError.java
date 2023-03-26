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


    private java.lang.String errorType;

    public java.lang.String getErrorType() {
        return this.errorType;
    }

    public void setErrorType(java.lang.String val) {
        this.errorType = val;
    }

    private java.lang.String message;

    public java.lang.String getMessage() {
        return this.message;
    }

    public void setMessage(java.lang.String val) {
        this.message = val;
    }

    private java.lang.String stacktrace;

    public java.lang.String getStacktrace() {
        return this.stacktrace;
    }

    public void setStacktrace(java.lang.String val) {
        this.stacktrace = val;
    }
}
