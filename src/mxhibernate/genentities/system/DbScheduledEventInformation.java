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

    private java.lang.String description;

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String val) {
        this.description = val;
    }

    private java.util.Date endTime;

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date val) {
        this.endTime = val;
    }

    private java.lang.String name;

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String val) {
        this.name = val;
    }

    private java.util.Date startTime;

    public java.util.Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(java.util.Date val) {
        this.startTime = val;
    }

    private java.lang.String status;

    public java.lang.String getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.String val) {
        this.status = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbXASInstance> scheduledEventInformation_XASInstance;

    public java.util.Set<mxhibernate.genentities.system.DbXASInstance> getScheduledEventInformation_XASInstance() {
        return this.scheduledEventInformation_XASInstance;
    }

    public void setScheduledEventInformation_XASInstance(java.util.Set<mxhibernate.genentities.system.DbXASInstance> val) {
        this.scheduledEventInformation_XASInstance = val;
    }
}
