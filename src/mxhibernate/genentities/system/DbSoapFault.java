/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbSoapFault extends mxhibernate.genentities.system.DbError
{
    public static final java.lang.String entityName = "System.SoapFault";

    public enum MemberNames
    {
        Code("Code"),
        Reason("Reason"),
        Node("Node"),
        Role("Role"),
        Detail("Detail"),
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

    private java.lang.String code;

    public java.lang.String getCode() {
        return this.code;
    }

    public void setCode(java.lang.String val) {
        this.code = val;
    }

    private java.lang.String detail;

    public java.lang.String getDetail() {
        return this.detail;
    }

    public void setDetail(java.lang.String val) {
        this.detail = val;
    }

    private java.lang.String node;

    public java.lang.String getNode() {
        return this.node;
    }

    public void setNode(java.lang.String val) {
        this.node = val;
    }

    private java.lang.String reason;

    public java.lang.String getReason() {
        return this.reason;
    }

    public void setReason(java.lang.String val) {
        this.reason = val;
    }

    private java.lang.String role;

    public java.lang.String getRole() {
        return this.role;
    }

    public void setRole(java.lang.String val) {
        this.role = val;
    }
}
