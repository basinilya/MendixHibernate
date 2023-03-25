/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbScheduledEventInformation
{
    public static final java.lang.String entityName = "System.ScheduledEventInformation";

    public enum MemberNames
    {
        Status("Status"),
        Description("Description"),
        EndTime("EndTime"),
        StartTime("StartTime"),
        Name("Name"),
        ScheduledEventInformation_XASInstance("System.ScheduledEventInformation_XASInstance");

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
