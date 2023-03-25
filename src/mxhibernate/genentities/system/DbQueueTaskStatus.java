/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public enum DbQueueTaskStatus
{
    Idle(new java.lang.String[][] { new java.lang.String[] { "en_US", "Idle" } }),
    Running(new java.lang.String[][] { new java.lang.String[] { "en_US", "Running" } }),
    Completed(new java.lang.String[][] { new java.lang.String[] { "en_US", "Completed" } }),
    Failed(new java.lang.String[][] { new java.lang.String[] { "en_US", "Failed" } }),
    Retrying(new java.lang.String[][] { new java.lang.String[] { "en_US", "Retrying" } }),
    Aborted(new java.lang.String[][] { new java.lang.String[] { "en_US", "Aborted" } }),
    Incompatible(new java.lang.String[][] { new java.lang.String[] { "en_US", "Incompatible" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbQueueTaskStatus(java.lang.String[][] captionStrings)
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
