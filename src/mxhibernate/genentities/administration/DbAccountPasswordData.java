/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.administration;

public class DbAccountPasswordData
{
    public static final java.lang.String entityName = "Administration.AccountPasswordData";

    public enum MemberNames
    {
        OldPassword("OldPassword"),
        NewPassword("NewPassword"),
        ConfirmPassword("ConfirmPassword"),
        AccountPasswordData_Account("Administration.AccountPasswordData_Account");

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


    public java.lang.String getConfirmPassword() {
        return null;
    }

    public void setConfirmPassword(java.lang.String val) {
    }

    public java.lang.String getNewPassword() {
        return null;
    }

    public void setNewPassword(java.lang.String val) {
    }

    public java.lang.String getOldPassword() {
        return null;
    }

    public void setOldPassword(java.lang.String val) {
    }
}
