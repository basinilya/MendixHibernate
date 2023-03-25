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


    public java.lang.String getXASId() {
        return null;
    }

    public void setXASId(java.lang.String val) {
    }

    public java.lang.String getQueueName() {
        return null;
    }

    public void setQueueName(java.lang.String val) {
    }

    public java.util.Date getValidUntil() {
        return null;
    }

    public void setValidUntil(java.util.Date val) {
    }
}
