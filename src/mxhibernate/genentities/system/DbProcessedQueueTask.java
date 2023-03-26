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


    private java.lang.String XASId;

    public java.lang.String getXASId() {
        return this.XASId;
    }

    public void setXASId(java.lang.String val) {
        this.XASId = val;
    }

    private java.lang.String arguments;

    public java.lang.String getArguments() {
        return this.arguments;
    }

    public void setArguments(java.lang.String val) {
        this.arguments = val;
    }

    private java.lang.String contextData;

    public java.lang.String getContextData() {
        return this.contextData;
    }

    public void setContextData(java.lang.String val) {
        this.contextData = val;
    }

    private java.lang.String contextType;

    public java.lang.String getContextType() {
        return this.contextType;
    }

    public void setContextType(java.lang.String val) {
        this.contextType = val;
    }

    private java.util.Date created;

    public java.util.Date getCreated() {
        return this.created;
    }

    public void setCreated(java.util.Date val) {
        this.created = val;
    }

    private java.lang.Long duration;

    public java.lang.Long getDuration() {
        return this.duration;
    }

    public void setDuration(java.lang.Long val) {
        this.duration = val;
    }

    private java.lang.String errorMessage;

    public java.lang.String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(java.lang.String val) {
        this.errorMessage = val;
    }

    private java.util.Date finished;

    public java.util.Date getFinished() {
        return this.finished;
    }

    public void setFinished(java.util.Date val) {
        this.finished = val;
    }

    private java.lang.String microflowName;

    public java.lang.String getMicroflowName() {
        return this.microflowName;
    }

    public void setMicroflowName(java.lang.String val) {
        this.microflowName = val;
    }

    private java.lang.String queueId;

    public java.lang.String getQueueId() {
        return this.queueId;
    }

    public void setQueueId(java.lang.String val) {
        this.queueId = val;
    }

    private java.lang.String queueName;

    public java.lang.String getQueueName() {
        return this.queueName;
    }

    public void setQueueName(java.lang.String val) {
        this.queueName = val;
    }

    private java.lang.Long retried;

    public java.lang.Long getRetried() {
        return this.retried;
    }

    public void setRetried(java.lang.Long val) {
        this.retried = val;
    }

    private java.lang.String scheduledEventName;

    public java.lang.String getScheduledEventName() {
        return this.scheduledEventName;
    }

    public void setScheduledEventName(java.lang.String val) {
        this.scheduledEventName = val;
    }

    private java.lang.Long sequence;

    public java.lang.Long getSequence() {
        return this.sequence;
    }

    public void setSequence(java.lang.Long val) {
        this.sequence = val;
    }

    private java.util.Date startAt;

    public java.util.Date getStartAt() {
        return this.startAt;
    }

    public void setStartAt(java.util.Date val) {
        this.startAt = val;
    }

    private java.util.Date started;

    public java.util.Date getStarted() {
        return this.started;
    }

    public void setStarted(java.util.Date val) {
        this.started = val;
    }

    private java.lang.String status;

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String val) {
        this.status = val;
    }

    private java.lang.Long threadId;

    public java.lang.Long getThreadId() {
        return this.threadId;
    }

    public void setThreadId(java.lang.Long val) {
        this.threadId = val;
    }

    private java.lang.String userActionName;

    public java.lang.String getUserActionName() {
        return this.userActionName;
    }

    public void setUserActionName(java.lang.String val) {
        this.userActionName = val;
    }
}
