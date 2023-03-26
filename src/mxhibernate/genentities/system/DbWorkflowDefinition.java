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

    private java.lang.String title;

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String val) {
        this.title = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflow> workflow_WorkflowDefinition;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflow> getWorkflow_WorkflowDefinition() {
        return this.workflow_WorkflowDefinition;
    }

    public void setWorkflow_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflow> val) {
        this.workflow_WorkflowDefinition = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> workflowUserTaskDefinition_WorkflowDefinition;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> getWorkflowUserTaskDefinition_WorkflowDefinition() {
        return this.workflowUserTaskDefinition_WorkflowDefinition;
    }

    public void setWorkflowUserTaskDefinition_WorkflowDefinition(java.util.Set<mxhibernate.genentities.system.DbWorkflowUserTaskDefinition> val) {
        this.workflowUserTaskDefinition_WorkflowDefinition = val;
    }
}
