/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbWorkflowUserTaskDefinition
{
    public static final java.lang.String entityName = "System.WorkflowUserTaskDefinition";

    public enum MemberNames
    {
        Name("Name"),
        IsObsolete("IsObsolete"),
        WorkflowUserTaskDefinition_WorkflowDefinition("System.WorkflowUserTaskDefinition_WorkflowDefinition");

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

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_WorkflowUserTaskDefinition() {
        return null;
    }

    public void setWorkflowUserTask_WorkflowUserTaskDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> getWorkflowUserTaskDefinition_WorkflowDefinition() {
        return null;
    }

    public void setWorkflowUserTaskDefinition_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> val) {
    }
}
