/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public enum DbDeviceType
{
    Phone(new java.lang.String[][] { new java.lang.String[] { "en_US", "Phone" }, new java.lang.String[] { "nl_NL", "Phone" } }),
    Tablet(new java.lang.String[][] { new java.lang.String[] { "en_US", "Tablet" }, new java.lang.String[] { "nl_NL", "Tablet" } }),
    Desktop(new java.lang.String[][] { new java.lang.String[] { "en_US", "Desktop" }, new java.lang.String[] { "nl_NL", "Desktop" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbDeviceType(java.lang.String[][] captionStrings)
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
