/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbScheduledEventInformation
{
    public static final java.lang.String entityName = "System.ScheduledEventInformation";

    public enum MemberNames
    {
        Name("Name"),
        Description("Description"),
        StartTime("StartTime"),
        EndTime("EndTime"),
        Status("Status"),
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


    public java.lang.String getDescription() {
        return null;
    }

    public void setDescription(java.lang.String val) {
    }

    public java.util.Date getEndTime() {
        return null;
    }

    public void setEndTime(java.util.Date val) {
    }

    public java.lang.String getName() {
        return null;
    }

    public void setName(java.lang.String val) {
    }

    public java.util.Date getStartTime() {
        return null;
    }

    public void setStartTime(java.util.Date val) {
    }

    public java.lang.String getStatus() {
        return null;
    }

    public void setStatus(java.lang.String val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbXASInstance> getScheduledEventInformation_XASInstance() {
        return null;
    }

    public void setScheduledEventInformation_XASInstance(java.util.Set<mxhibernate.genentities.system.DbXASInstance> val) {
    }
}
