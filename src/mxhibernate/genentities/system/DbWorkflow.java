/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbWorkflow
{
    public static final java.lang.String entityName = "System.Workflow";

    public enum MemberNames
    {
        Name("Name"),
        Description("Description"),
        StartTime("StartTime"),
        EndTime("EndTime"),
        DueDate("DueDate"),
        CanBeRestarted("CanBeRestarted"),
        CanBeContinued("CanBeContinued"),
        State("State"),
        Reason("Reason"),
        Workflow_WorkflowDefinition("System.Workflow_WorkflowDefinition");

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

    private java.lang.Boolean canBeContinued;

    public java.lang.Boolean getCanBeContinued() {
        return this.canBeContinued;
    }

    public void setCanBeContinued(java.lang.Boolean val) {
        this.canBeContinued = val;
    }

    private java.lang.Boolean canBeRestarted;

    public java.lang.Boolean getCanBeRestarted() {
        return this.canBeRestarted;
    }

    public void setCanBeRestarted(java.lang.Boolean val) {
        this.canBeRestarted = val;
    }

    private java.lang.String description;

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String val) {
        this.description = val;
    }

    private java.util.Date dueDate;

    public java.util.Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(java.util.Date val) {
        this.dueDate = val;
    }

    private java.util.Date endTime;

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date val) {
        this.endTime = val;
    }

    private java.lang.String name;

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String val) {
        this.name = val;
    }

    private java.lang.String reason;

    public java.lang.String getReason() {
        return this.reason;
    }

    public void setReason(java.lang.String val) {
        this.reason = val;
    }

    private java.util.Date startTime;

    public java.util.Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(java.util.Date val) {
        this.startTime = val;
    }

    private java.lang.String state;

    public java.lang.String getState() {
        return this.state;
    }

    public void setState(java.lang.String val) {
        this.state = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> workflow_WorkflowDefinition;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> getWorkflow_WorkflowDefinition() {
        return this.workflow_WorkflowDefinition;
    }

    public void setWorkflow_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> val) {
        this.workflow_WorkflowDefinition = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> workflowUserTask_Workflow;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_Workflow() {
        return this.workflowUserTask_Workflow;
    }

    public void setWorkflowUserTask_Workflow(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
        this.workflowUserTask_Workflow = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> workflowJumpToDetails_Workflow;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> getWorkflowJumpToDetails_Workflow() {
        return this.workflowJumpToDetails_Workflow;
    }

    public void setWorkflowJumpToDetails_Workflow(java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> val) {
        this.workflowJumpToDetails_Workflow = val;
    }
}
