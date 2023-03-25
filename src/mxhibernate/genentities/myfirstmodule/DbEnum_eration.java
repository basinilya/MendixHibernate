/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.myfirstmodule;

public enum DbEnum_eration
{
    a_a(new java.lang.String[][] { new java.lang.String[] { "en_US", "a-a" } }),
    b_b(new java.lang.String[][] { new java.lang.String[] { "en_US", "b-b" } });

    private final java.util.Map<java.lang.String, java.lang.String> captions;

    private DbEnum_eration(java.lang.String[][] captionStrings)
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
