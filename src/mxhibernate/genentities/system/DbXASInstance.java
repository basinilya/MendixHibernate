/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbXASInstance
{
    public static final java.lang.String entityName = "System.XASInstance";

    public enum MemberNames
    {
        XASId("XASId"),
        LastUpdate("LastUpdate"),
        AllowedNumberOfConcurrentUsers("AllowedNumberOfConcurrentUsers"),
        PartnerName("PartnerName"),
        CustomerName("CustomerName");

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

    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long val) {
        this.id = val;
    }

    private java.lang.String XASId;

    public java.lang.String getXASId() {
        return this.XASId;
    }

    public void setXASId(java.lang.String val) {
        this.XASId = val;
    }

    private java.lang.Integer allowedNumberOfConcurrentUsers;

    public java.lang.Integer getAllowedNumberOfConcurrentUsers() {
        return this.allowedNumberOfConcurrentUsers;
    }

    public void setAllowedNumberOfConcurrentUsers(java.lang.Integer val) {
        this.allowedNumberOfConcurrentUsers = val;
    }

    private java.lang.String customerName;

    public java.lang.String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(java.lang.String val) {
        this.customerName = val;
    }

    private java.util.Date lastUpdate;

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(java.util.Date val) {
        this.lastUpdate = val;
    }

    private java.lang.String partnerName;

    public java.lang.String getPartnerName() {
        return this.partnerName;
    }

    public void setPartnerName(java.lang.String val) {
        this.partnerName = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbScheduledEventInformation> scheduledEventInformation_XASInstance;

    public java.util.Set<mxhibernate.genentities.system.DbScheduledEventInformation> getScheduledEventInformation_XASInstance() {
        return this.scheduledEventInformation_XASInstance;
    }

    public void setScheduledEventInformation_XASInstance(java.util.Set<mxhibernate.genentities.system.DbScheduledEventInformation> val) {
        this.scheduledEventInformation_XASInstance = val;
    }
}
