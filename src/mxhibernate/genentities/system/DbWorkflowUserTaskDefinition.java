/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbWorkflowUserTaskDefinition
{
    public static final java.lang.String entityName = "System.WorkflowUserTaskDefinition";

    public enum MemberNames
    {
        ModelGUID("ModelGUID"),
        IsObsolete("IsObsolete"),
        Name("Name"),
        WorkflowUserTaskDefinition_WorkflowDefinition("System.WorkflowUserTaskDefinition_WorkflowDefinition"),
        WorkflowVersion_WorkflowUserTaskDefinition("System.WorkflowVersion_WorkflowUserTaskDefinition"),
        WorkflowUserTask_WorkflowUserTaskDefinition("System.WorkflowUserTask_WorkflowUserTaskDefinition");

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
