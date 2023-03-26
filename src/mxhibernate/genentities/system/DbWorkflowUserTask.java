/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbWorkflowUserTask
{
    public static final java.lang.String entityName = "System.WorkflowUserTask";

    public enum MemberNames
    {
        Name("Name"),
        Description("Description"),
        StartTime("StartTime"),
        DueDate("DueDate"),
        EndTime("EndTime"),
        Outcome("Outcome"),
        State("State"),
        WorkflowUserTask_TargetUsers("System.WorkflowUserTask_TargetUsers"),
        WorkflowUserTask_Assignee("System.WorkflowUserTask_Assignee"),
        WorkflowUserTask_Workflow("System.WorkflowUserTask_Workflow"),
        WorkflowUserTask_WorkflowUserTaskDefinition("System.WorkflowUserTask_WorkflowUserTaskDefinition");

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

    private java.lang.String outcome;

    public java.lang.String getOutcome() {
        return this.outcome;
    }

    public void setOutcome(java.lang.String val) {
        this.outcome = val;
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

    private java.util.Set<mxhibernate.genentities.system.DbWorkflow> workflowUserTask_Workflow;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflow> getWorkflowUserTask_Workflow() {
        return this.workflowUserTask_Workflow;
    }

    public void setWorkflowUserTask_Workflow(java.util.Set<mxhibernate.genentities.system.DbWorkflow> val) {
        this.workflowUserTask_Workflow = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> workflowUserTask_WorkflowUserTaskDefinition;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> getWorkflowUserTask_WorkflowUserTaskDefinition() {
        return this.workflowUserTask_WorkflowUserTaskDefinition;
    }

    public void setWorkflowUserTask_WorkflowUserTaskDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> val) {
        this.workflowUserTask_WorkflowUserTaskDefinition = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> workflowUserTask_TargetUsers;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getWorkflowUserTask_TargetUsers() {
        return this.workflowUserTask_TargetUsers;
    }

    public void setWorkflowUserTask_TargetUsers(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.workflowUserTask_TargetUsers = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> workflowUserTask_Assignee;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getWorkflowUserTask_Assignee() {
        return this.workflowUserTask_Assignee;
    }

    public void setWorkflowUserTask_Assignee(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.workflowUserTask_Assignee = val;
    }
}
