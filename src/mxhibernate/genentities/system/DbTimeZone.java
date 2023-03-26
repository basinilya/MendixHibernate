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


    public java.lang.String getCode() {
        return null;
    }

    public void setCode(java.lang.String val) {
    }

    public java.lang.String getDescription() {
        return null;
    }

    public void setDescription(java.lang.String val) {
    }

    public java.lang.Integer getRawOffset() {
        return null;
    }

    public void setRawOffset(java.lang.Integer val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbUser> getUser_TimeZone() {
        return null;
    }

    public void setUser_TimeZone(java.util.Set<mxhibernate.genentities.system.DbUser> val) {
    }
}
