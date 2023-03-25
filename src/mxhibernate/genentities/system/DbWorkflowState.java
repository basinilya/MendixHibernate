/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public enum DbWorkflowState
{
    InProgress(new java.lang.String[][] { new java.lang.String[] { "en_US", "In Progress" } }),
    Paused(new java.lang.String[][] { new java.lang.String[] { "en_US", "Paused" } }),
    Completed(new java.lang.String[][] { new java.lang.String[] { "en_US", "Completed" } }),
    Aborted(new java.lang.String[][] { new java.lang.String[] { "en_US", "Aborted" } }),
    Incompatible(new java.lang.String[][] { new java.lang.String[] { "en_US", "Incompatible" } }),
    Failed(new java.lang.String[][] { new java.lang.String[] { "en_US", "Failed" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbWorkflowState(java.lang.String[][] captionStrings)
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
