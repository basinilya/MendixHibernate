/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public enum DbEventStatus
{
    Running(new java.lang.String[][] { new java.lang.String[] { "en_US", "Running" }, new java.lang.String[] { "nl_NL", "Bezig" } }),
    Completed(new java.lang.String[][] { new java.lang.String[] { "en_US", "Completed" }, new java.lang.String[] { "nl_NL", "Voltooid" } }),
    Error(new java.lang.String[][] { new java.lang.String[] { "en_US", "Error" }, new java.lang.String[] { "nl_NL", "Fout" } }),
    Stopped(new java.lang.String[][] { new java.lang.String[] { "en_US", "Stopped" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbEventStatus(java.lang.String[][] captionStrings)
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
