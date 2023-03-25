/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbXASInstance
{
    public static final java.lang.String entityName = "System.XASInstance";

    public enum MemberNames
    {
        createdDate("createdDate"),
        XASId("XASId"),
        LastUpdate("LastUpdate"),
        AllowedNumberOfConcurrentUsers("AllowedNumberOfConcurrentUsers"),
        CustomerName("CustomerName"),
        PartnerName("PartnerName"),
        UnreferencedFile_XASInstance("System.UnreferencedFile_XASInstance"),
        ScheduledEventInformation_XASInstance("System.ScheduledEventInformation_XASInstance"),
        BackgroundJob_XASInstance("System.BackgroundJob_XASInstance");

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

}
