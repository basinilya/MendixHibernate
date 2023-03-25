/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbQueuedTask
{
    public static final java.lang.String entityName = "System.QueuedTask";

    public enum MemberNames
    {
        Status("Status"),
        ContextType("ContextType"),
        Started("Started"),
        Retried("Retried"),
        Arguments("Arguments"),
        QueueId("QueueId"),
        Sequence("Sequence"),
        Created("Created"),
        MicroflowName("MicroflowName"),
        UserActionName("UserActionName"),
        XASId("XASId"),
        ThreadId("ThreadId"),
        StartAt("StartAt"),
        ScheduledEventName("ScheduledEventName"),
        ContextData("ContextData"),
        Retry("Retry"),
        QueueName("QueueName"),
        owner("System.owner");

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
