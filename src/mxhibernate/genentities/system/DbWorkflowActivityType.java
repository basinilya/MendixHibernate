/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public enum DbWorkflowActivityType
{
    Start(new java.lang.String[][] { new java.lang.String[] { "en_US", "Start" } }),
    End(new java.lang.String[][] { new java.lang.String[] { "en_US", "End" } }),
    ExclusiveSplit(new java.lang.String[][] { new java.lang.String[] { "en_US", "Decision" } }),
    ParallelSplit(new java.lang.String[][] { new java.lang.String[] { "en_US", "Parallel Split" } }),
    ParallelSplitBranchStopper(new java.lang.String[][] { new java.lang.String[] { "en_US", "End of Parallel Split Branch" } }),
    ParallelSplitMerge(new java.lang.String[][] { new java.lang.String[] { "en_US", "Merge of Parallel Split" } }),
    UserTask(new java.lang.String[][] { new java.lang.String[] { "en_US", "User task" } }),
    CallMicroflow(new java.lang.String[][] { new java.lang.String[] { "en_US", "Call Microflow" } }),
    CallWorkflow(new java.lang.String[][] { new java.lang.String[] { "en_US", "Call Workflow" } }),
    JumpTo(new java.lang.String[][] { new java.lang.String[] { "en_US", "Jump" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbWorkflowActivityType(java.lang.String[][] captionStrings)
    {
        this.captions = new java.util.HashMap<>();
        for (java.lang.String[] captionString : captionStrings) {
            captions.put(captionString[0], captionString[1]);
        }
    }

    public java.lang.String getCaption(java.lang.String languageCode)
    {
        return captions.getOrDefault(languageCode, "en_US");
    }

    public java.lang.String getCaption()
    {
        return captions.get("en_US");
    }

}
