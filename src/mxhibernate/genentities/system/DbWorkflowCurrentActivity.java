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


    public java.lang.String getAction() {
        return null;
    }

    public void setAction(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> getWorkflowCurrentActivity_JumpToTarget() {
        return null;
    }

    public void setWorkflowCurrentActivity_JumpToTarget(java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> getWorkflowCurrentActivity_ActivityDetails() {
        return null;
    }

    public void setWorkflowCurrentActivity_ActivityDetails(java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> getWorkflowJumpToDetails_CurrentActivities() {
        return null;
    }

    public void setWorkflowJumpToDetails_CurrentActivities(java.util.Set<mxhibernate.genentities.system.DbWorkflowJumpToDetails> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> getWorkflowCurrentActivity_ApplicableTargets() {
        return null;
    }

    public void setWorkflowCurrentActivity_ApplicableTargets(java.util.Set<mxhibernate.genentities.system.DbWorkflowActivityDetails> val) {
    }
}
