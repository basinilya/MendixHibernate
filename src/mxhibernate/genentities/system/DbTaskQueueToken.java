/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbTaskQueueToken
{
    public static final java.lang.String entityName = "System.TaskQueueToken";

    public enum MemberNames
    {
        XASId("XASId"),
        QueueName("QueueName"),
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

}
