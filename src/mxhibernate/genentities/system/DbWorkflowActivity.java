/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbWorkflowActivity
{
    public static final java.lang.String entityName = "System.WorkflowActivity";

    public enum MemberNames
    {
        ModelGUID("ModelGUID"),
        ActivityGUID("ActivityGUID"),
        EndTime("EndTime"),
        ActivityHash("ActivityHash"),
        Outcome("Outcome"),
        State("State"),
        StartTime("StartTime"),
        Error("Error"),
        IsDerivedActivity("IsDerivedActivity"),
        OutcomeModelGUID("OutcomeModelGUID"),
        Caption("Caption"),
        IsMigrationActivity("IsMigrationActivity"),
        WorkflowActivity_TaskActor("System.WorkflowActivity_TaskActor"),
        WorkflowActivity_Workflow("System.WorkflowActivity_Workflow"),
        WorkflowActivity_WorkflowUserTask("System.WorkflowActivity_WorkflowUserTask"),
        WorkflowActivity_PreviousActivity("System.WorkflowActivity_PreviousActivity"),
        WorkflowActivity_WorkflowVersion("System.WorkflowActivity_WorkflowVersion"),
        Workflow_CurrentActivity("System.Workflow_CurrentActivity");

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
