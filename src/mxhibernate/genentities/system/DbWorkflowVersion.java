/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbWorkflowVersion
{
    public static final java.lang.String entityName = "System.WorkflowVersion";

    public enum MemberNames
    {
        VersionHash("VersionHash"),
        ModelJSON("ModelJSON"),
        WorkflowVersion_WorkflowUserTaskDefinition("System.WorkflowVersion_WorkflowUserTaskDefinition"),
        WorkflowVersion_WorkflowDefinition("System.WorkflowVersion_WorkflowDefinition"),
        WorkflowVersion_PreviousVersion("System.WorkflowVersion_PreviousVersion"),
        WorkflowDefinition_CurrentWorkflowVersion("System.WorkflowDefinition_CurrentWorkflowVersion"),
        WorkflowActivity_WorkflowVersion("System.WorkflowActivity_WorkflowVersion");

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
