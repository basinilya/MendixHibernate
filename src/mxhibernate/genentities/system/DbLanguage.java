/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbLanguage
{
    public static final java.lang.String entityName = "System.Language";

    public enum MemberNames
    {
        Code("Code"),
        Description("Description");

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


    private java.lang.String code;

    public java.lang.String getCode() {
        return this.code;
    }

    public void setCode(java.lang.String val) {
        this.code = val;
    }

    private java.lang.String description;

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String val) {
        this.description = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> user_Language;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getUser_Language() {
        return this.user_Language;
    }

    public void setUser_Language(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.user_Language = val;
    }
}
