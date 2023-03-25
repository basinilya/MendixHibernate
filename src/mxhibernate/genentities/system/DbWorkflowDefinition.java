/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbWorkflowDefinition
{
    public static final java.lang.String entityName = "System.WorkflowDefinition";

    public enum MemberNames
    {
        ModelGUID("ModelGUID"),
        IsObsolete("IsObsolete"),
        Title("Title"),
        Name("Name"),
        WorkflowDefinition_CurrentWorkflowVersion("System.WorkflowDefinition_CurrentWorkflowVersion"),
        Workflow_WorkflowDefinition("System.Workflow_WorkflowDefinition"),
        WorkflowUserTaskDefinition_WorkflowDefinition("System.WorkflowUserTaskDefinition_WorkflowDefinition"),
        WorkflowVersion_WorkflowDefinition("System.WorkflowVersion_WorkflowDefinition");

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

}
