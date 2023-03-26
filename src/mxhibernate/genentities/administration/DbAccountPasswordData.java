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

    private java.lang.String confirmPassword;

    public java.lang.String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(java.lang.String val) {
        this.confirmPassword = val;
    }

    private java.lang.String newPassword;

    public java.lang.String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(java.lang.String val) {
        this.newPassword = val;
    }

    private java.lang.String oldPassword;

    public java.lang.String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(java.lang.String val) {
        this.oldPassword = val;
    }

    private java.util.Set<mxhibernate.genentities.administration.DbAccount> accountPasswordData_Account;

    public java.util.Set<mxhibernate.genentities.administration.DbAccount> getAccountPasswordData_Account() {
        return this.accountPasswordData_Account;
    }

    public void setAccountPasswordData_Account(java.util.Set<mxhibernate.genentities.administration.DbAccount> val) {
        this.accountPasswordData_Account = val;
    }
}
