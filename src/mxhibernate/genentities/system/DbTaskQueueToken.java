/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbTaskQueueToken
{
    public static final java.lang.String entityName = "System.TaskQueueToken";

    public enum MemberNames
    {
        QueueName("QueueName"),
        XASId("XASId"),
        ValidUntil("ValidUntil");

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

    private java.lang.String XASId;

    public java.lang.String getXASId() {
        return this.XASId;
    }

    public void setXASId(java.lang.String val) {
        this.XASId = val;
    }

    private java.lang.String queueName;

    public java.lang.String getQueueName() {
        return this.queueName;
    }

    public void setQueueName(java.lang.String val) {
        this.queueName = val;
    }

    private java.util.Date validUntil;

    public java.util.Date getValidUntil() {
        return this.validUntil;
    }

    public void setValidUntil(java.util.Date val) {
        this.validUntil = val;
    }
}
