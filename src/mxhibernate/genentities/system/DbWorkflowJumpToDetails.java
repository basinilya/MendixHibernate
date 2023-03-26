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


    public java.lang.String getError() {
        return null;
    }

    public void setError(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflow> getWorkflowJumpToDetails_Workflow() {
        return null;
    }

    public void setWorkflowJumpToDetails_Workflow(java.util.Set<mxhibernate.genentities.system.DbWorkflow> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowJumpToDetails_CurrentActivities() {
        return null;
    }

    public void setWorkflowJumpToDetails_CurrentActivities(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
    }
}
