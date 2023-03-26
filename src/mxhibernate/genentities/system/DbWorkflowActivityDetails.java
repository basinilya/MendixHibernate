/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbWorkflowActivityDetails
{
    public static final java.lang.String entityName = "System.WorkflowActivityDetails";

    public enum MemberNames
    {
        ActivityId("ActivityId"),
        ActivityCaption("ActivityCaption"),
        ActivityType("ActivityType"),
        ExistsInCurrentVersion("ExistsInCurrentVersion");

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

    private java.lang.String activityCaption;

    public java.lang.String getActivityCaption() {
        return this.activityCaption;
    }

    public void setActivityCaption(java.lang.String val) {
        this.activityCaption = val;
    }

    private java.lang.String activityId;

    public java.lang.String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(java.lang.String val) {
        this.activityId = val;
    }

    private java.lang.String activityType;

    public java.lang.String getActivityType() {
        return this.activityType;
    }

    public void setActivityType(java.lang.String val) {
        this.activityType = val;
    }

    private java.lang.Boolean existsInCurrentVersion;

    public java.lang.Boolean getExistsInCurrentVersion() {
        return this.existsInCurrentVersion;
    }

    public void setExistsInCurrentVersion(java.lang.Boolean val) {
        this.existsInCurrentVersion = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> workflowCurrentActivity_JumpToTarget;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowCurrentActivity_JumpToTarget() {
        return this.workflowCurrentActivity_JumpToTarget;
    }

    public void setWorkflowCurrentActivity_JumpToTarget(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
        this.workflowCurrentActivity_JumpToTarget = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> workflowCurrentActivity_ActivityDetails;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowCurrentActivity_ActivityDetails() {
        return this.workflowCurrentActivity_ActivityDetails;
    }

    public void setWorkflowCurrentActivity_ActivityDetails(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
        this.workflowCurrentActivity_ActivityDetails = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> workflowCurrentActivity_ApplicableTargets;

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowCurrentActivity_ApplicableTargets() {
        return this.workflowCurrentActivity_ApplicableTargets;
    }

    public void setWorkflowCurrentActivity_ApplicableTargets(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
        this.workflowCurrentActivity_ApplicableTargets = val;
    }
}
