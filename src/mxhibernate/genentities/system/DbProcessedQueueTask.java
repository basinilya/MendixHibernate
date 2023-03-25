/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbProcessedQueueTask
{
    public static final java.lang.String entityName = "System.ProcessedQueueTask";

    public enum MemberNames
    {
        Sequence("Sequence"),
        Status("Status"),
        QueueId("QueueId"),
        QueueName("QueueName"),
        ContextType("ContextType"),
        ContextData("ContextData"),
        MicroflowName("MicroflowName"),
        UserActionName("UserActionName"),
        Arguments("Arguments"),
        XASId("XASId"),
        ThreadId("ThreadId"),
        Created("Created"),
        StartAt("StartAt"),
        Started("Started"),
        Finished("Finished"),
        Duration("Duration"),
        Retried("Retried"),
        ErrorMessage("ErrorMessage"),
        ScheduledEventName("ScheduledEventName");

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

    public java.lang.String getArguments() {
        return null;
    }

    public void setArguments(java.lang.String val) {
    }

    public java.lang.String getContextData() {
        return null;
    }

    public void setContextData(java.lang.String val) {
    }

    public mxhibernate.genentities.system.DbContextType getContextType() {
        return null;
    }

    public void setContextType(mxhibernate.genentities.system.DbContextType val) {
    }

    public java.util.Date getCreated() {
        return null;
    }

    public void setCreated(java.util.Date val) {
    }

    public java.lang.Long getDuration() {
        return null;
    }

    public void setDuration(java.lang.Long val) {
    }

    public java.lang.String getErrorMessage() {
        return null;
    }

    public void setErrorMessage(java.lang.String val) {
    }

    public java.util.Date getFinished() {
        return null;
    }

    public void setFinished(java.util.Date val) {
    }

    public java.lang.String getMicroflowName() {
        return null;
    }

    public void setMicroflowName(java.lang.String val) {
    }

    public java.lang.String getQueueId() {
        return null;
    }

    public void setQueueId(java.lang.String val) {
    }

    public java.lang.String getQueueName() {
        return null;
    }

    public void setQueueName(java.lang.String val) {
    }

    public java.lang.Long getRetried() {
        return null;
    }

    public void setRetried(java.lang.Long val) {
    }

    public java.lang.String getScheduledEventName() {
        return null;
    }

    public void setScheduledEventName(java.lang.String val) {
    }

    public java.lang.Long getSequence() {
        return null;
    }

    public void setSequence(java.lang.Long val) {
    }

    public java.util.Date getStartAt() {
        return null;
    }

    public void setStartAt(java.util.Date val) {
    }

    public java.util.Date getStarted() {
        return null;
    }

    public void setStarted(java.util.Date val) {
    }

    public mxhibernate.genentities.system.DbQueueTaskStatus getStatus() {
        return null;
    }

    public void setStatus(mxhibernate.genentities.system.DbQueueTaskStatus val) {
    }

    public java.lang.Long getThreadId() {
        return null;
    }

    public void setThreadId(java.lang.Long val) {
    }

    public java.lang.String getUserActionName() {
        return null;
    }

    public void setUserActionName(java.lang.String val) {
    }
}