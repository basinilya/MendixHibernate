/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public enum DbUserType
{
    Internal(new java.lang.String[][] { new java.lang.String[] { "en_US", "Internal" }, new java.lang.String[] { "nl_NL", "Intern" } }),
    External(new java.lang.String[][] { new java.lang.String[] { "en_US", "External" }, new java.lang.String[] { "nl_NL", "Extern" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbUserType(java.lang.String[][] captionStrings)
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
