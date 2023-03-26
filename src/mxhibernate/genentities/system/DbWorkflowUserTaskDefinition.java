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

    private java.lang.Boolean isObsolete;

    public java.lang.Boolean getIsObsolete() {
        return this.isObsolete;
    }

    public void setIsObsolete(java.lang.Boolean val) {
        this.isObsolete = val;
    }

    private java.lang.String name;

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String val) {
        this.name = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> workflowUserTask_WorkflowUserTaskDefinition;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> getWorkflowUserTask_WorkflowUserTaskDefinition() {
        return this.workflowUserTask_WorkflowUserTaskDefinition;
    }

    public void setWorkflowUserTask_WorkflowUserTaskDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTask> val) {
        this.workflowUserTask_WorkflowUserTaskDefinition = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> workflowUserTaskDefinition_WorkflowDefinition;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> getWorkflowUserTaskDefinition_WorkflowDefinition() {
        return this.workflowUserTaskDefinition_WorkflowDefinition;
    }

    public void setWorkflowUserTaskDefinition_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowDefinition> val) {
        this.workflowUserTaskDefinition_WorkflowDefinition = val;
    }
}
