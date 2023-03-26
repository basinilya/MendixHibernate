/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbWorkflowCurrentActivity
{
    public static final java.lang.String entityName = "System.WorkflowCurrentActivity";

    public enum MemberNames
    {
        Action("Action"),
        WorkflowCurrentActivity_ActivityDetails("System.WorkflowCurrentActivity_ActivityDetails"),
        WorkflowCurrentActivity_ApplicableTargets("System.WorkflowCurrentActivity_ApplicableTargets"),
        WorkflowCurrentActivity_JumpToTarget("System.WorkflowCurrentActivity_JumpToTarget");

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

    private java.lang.String action;

    public java.lang.String getAction() {
        return this.action;
    }

    public void setAction(java.lang.String val) {
        this.action = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> workflowCurrentActivity_JumpToTarget;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> getWorkflowCurrentActivity_JumpToTarget() {
        return this.workflowCurrentActivity_JumpToTarget;
    }

    public void setWorkflowCurrentActivity_JumpToTarget(java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> val) {
        this.workflowCurrentActivity_JumpToTarget = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> workflowCurrentActivity_ActivityDetails;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> getWorkflowCurrentActivity_ActivityDetails() {
        return this.workflowCurrentActivity_ActivityDetails;
    }

    public void setWorkflowCurrentActivity_ActivityDetails(java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> val) {
        this.workflowCurrentActivity_ActivityDetails = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> workflowJumpToDetails_CurrentActivities;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> getWorkflowJumpToDetails_CurrentActivities() {
        return this.workflowJumpToDetails_CurrentActivities;
    }

    public void setWorkflowJumpToDetails_CurrentActivities(java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> val) {
        this.workflowJumpToDetails_CurrentActivities = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> workflowCurrentActivity_ApplicableTargets;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> getWorkflowCurrentActivity_ApplicableTargets() {
        return this.workflowCurrentActivity_ApplicableTargets;
    }

    public void setWorkflowCurrentActivity_ApplicableTargets(java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> val) {
        this.workflowCurrentActivity_ApplicableTargets = val;
    }
}
