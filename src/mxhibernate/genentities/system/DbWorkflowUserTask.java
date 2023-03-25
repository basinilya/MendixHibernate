/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbWorkflowUserTask
{
    public static final java.lang.String entityName = "System.WorkflowUserTask";

    public enum MemberNames
    {
        Description("Description"),
        EndTime("EndTime"),
        Outcome("Outcome"),
        State("State"),
        StartTime("StartTime"),
        Error("Error"),
        DueDate("DueDate"),
        Name("Name"),
        ProcessingState("ProcessingState"),
        WorkflowUserTask_TargetUsers("System.WorkflowUserTask_TargetUsers"),
        WorkflowUserTask_Assignee("System.WorkflowUserTask_Assignee"),
        WorkflowUserTask_Workflow("System.WorkflowUserTask_Workflow"),
        WorkflowUserTask_WorkflowUserTaskDefinition("System.WorkflowUserTask_WorkflowUserTaskDefinition"),
        WorkflowActivity_WorkflowUserTask("System.WorkflowActivity_WorkflowUserTask");

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
