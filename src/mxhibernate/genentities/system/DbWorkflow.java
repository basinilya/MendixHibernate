/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbWorkflow
{
    public static final java.lang.String entityName = "System.Workflow";

    public enum MemberNames
    {
        Description("Description"),
        EndTime("EndTime"),
        ObjectId("ObjectId"),
        CanBeContinued("CanBeContinued"),
        State("State"),
        CanBeRestarted("CanBeRestarted"),
        StartTime("StartTime"),
        DueDate("DueDate"),
        PreviousState("PreviousState"),
        Reason("Reason"),
        Name("Name"),
        ProcessingState("ProcessingState"),
        Workflow_WorkflowDefinition("System.Workflow_WorkflowDefinition"),
        owner("System.owner"),
        Workflow_CurrentActivity("System.Workflow_CurrentActivity"),
        WorkflowActivity_Workflow("System.WorkflowActivity_Workflow"),
        WorkflowUserTask_Workflow("System.WorkflowUserTask_Workflow");

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
