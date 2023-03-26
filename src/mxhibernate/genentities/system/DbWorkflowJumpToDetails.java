/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbWorkflowJumpToDetails
{
    public static final java.lang.String entityName = "System.WorkflowJumpToDetails";

    public enum MemberNames
    {
        Error("Error"),
        WorkflowJumpToDetails_Workflow("System.WorkflowJumpToDetails_Workflow"),
        WorkflowJumpToDetails_CurrentActivities("System.WorkflowJumpToDetails_CurrentActivities");

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

    private java.lang.String error;

    public java.lang.String getError() {
        return this.error;
    }

    public void setError(java.lang.String val) {
        this.error = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflow> workflowJumpToDetails_Workflow;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflow> getWorkflowJumpToDetails_Workflow() {
        return this.workflowJumpToDetails_Workflow;
    }

    public void setWorkflowJumpToDetails_Workflow(java.util.Set<mxhibernate.genentities.system.DbWorkflow> val) {
        this.workflowJumpToDetails_Workflow = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> workflowJumpToDetails_CurrentActivities;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowJumpToDetails_CurrentActivities() {
        return this.workflowJumpToDetails_CurrentActivities;
    }

    public void setWorkflowJumpToDetails_CurrentActivities(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
        this.workflowJumpToDetails_CurrentActivities = val;
    }
}
