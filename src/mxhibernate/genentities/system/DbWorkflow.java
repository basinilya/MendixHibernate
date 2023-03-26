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


    public java.lang.Boolean getCanBeContinued() {
        return null;
    }

    public void setCanBeContinued(java.lang.Boolean val) {
    }

    public java.lang.Boolean getCanBeRestarted() {
        return null;
    }

    public void setCanBeRestarted(java.lang.Boolean val) {
    }

    public java.lang.String getDescription() {
        return null;
    }

    public void setDescription(java.lang.String val) {
    }

    public java.util.Date getDueDate() {
        return null;
    }

    public void setDueDate(java.util.Date val) {
    }

    public java.util.Date getEndTime() {
        return null;
    }

    public void setEndTime(java.util.Date val) {
    }

    public java.lang.String getName() {
        return null;
    }

    public void setName(java.lang.String val) {
    }

    public java.lang.String getReason() {
        return null;
    }

    public void setReason(java.lang.String val) {
    }

    public java.util.Date getStartTime() {
        return null;
    }

    public void setStartTime(java.util.Date val) {
    }

    public java.lang.String getState() {
        return null;
    }

    public void setState(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> getWorkflow_WorkflowDefinition() {
        return null;
    }

    public void setWorkflow_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_Workflow() {
        return null;
    }

    public void setWorkflowUserTask_Workflow(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> getWorkflowJumpToDetails_Workflow() {
        return null;
    }

    public void setWorkflowJumpToDetails_Workflow(java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> val) {
    }
}
