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


    public mxhibernate.genentities.system.DbWorkflowCurrentActivityAction getAction() {
        return null;
    }

    public void setAction(mxhibernate.genentities.system.DbWorkflowCurrentActivityAction val) {
    }
}