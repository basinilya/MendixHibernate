/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbTimeZone
{
    public static final java.lang.String entityName = "System.TimeZone";

    public enum MemberNames
    {
        Code("Code"),
        Description("Description"),
        RawOffset("RawOffset");

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

    private java.lang.Integer rawOffset;

    public java.lang.Integer getRawOffset() {
        return this.rawOffset;
    }

    public void setRawOffset(java.lang.Integer val) {
        this.rawOffset = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbUser> user_TimeZone;

    public java.util.Set<mxhibernate.genentities.system.DbUser> getUser_TimeZone() {
        return this.user_TimeZone;
    }

    public void setUser_TimeZone(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
        this.user_TimeZone = val;
    }
}
