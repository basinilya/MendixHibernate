/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbWorkflowDefinition
{
    public static final java.lang.String entityName = "System.WorkflowDefinition";

    public enum MemberNames
    {
        Name("Name"),
        Title("Title"),
        IsObsolete("IsObsolete");

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


    public java.lang.Boolean getIsObsolete() {
        return null;
    }

    public void setIsObsolete(java.lang.Boolean val) {
    }

    public java.lang.String getName() {
        return null;
    }

    public void setName(java.lang.String val) {
    }

    public java.lang.String getTitle() {
        return null;
    }

    public void setTitle(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflow> getWorkflow_WorkflowDefinition() {
        return null;
    }

    public void setWorkflow_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflow> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> getWorkflowUserTaskDefinition_WorkflowDefinition() {
        return null;
    }

    public void setWorkflowUserTaskDefinition_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> val) {
    }
}
