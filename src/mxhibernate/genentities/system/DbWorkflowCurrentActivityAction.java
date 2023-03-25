/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public enum DbWorkflowCurrentActivityAction
{
    DoNothing(new java.lang.String[][] { new java.lang.String[] { "en_US", "Do Nothing" } }),
    JumpTo(new java.lang.String[][] { new java.lang.String[] { "en_US", "Jump To" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbWorkflowCurrentActivityAction(java.lang.String[][] captionStrings)
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
