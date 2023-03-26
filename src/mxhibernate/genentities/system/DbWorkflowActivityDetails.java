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


    public java.lang.String getActivityCaption() {
        return null;
    }

    public void setActivityCaption(java.lang.String val) {
    }

    public java.lang.String getActivityId() {
        return null;
    }

    public void setActivityId(java.lang.String val) {
    }

    public java.lang.String getActivityType() {
        return null;
    }

    public void setActivityType(java.lang.String val) {
    }

    public java.lang.Boolean getExistsInCurrentVersion() {
        return null;
    }

    public void setExistsInCurrentVersion(java.lang.Boolean val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowCurrentActivity_JumpToTarget() {
        return null;
    }

    public void setWorkflowCurrentActivity_JumpToTarget(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowCurrentActivity_ActivityDetails() {
        return null;
    }

    public void setWorkflowCurrentActivity_ActivityDetails(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
    }

    public java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> getWorkflowCurrentActivity_ApplicableTargets() {
        return null;
    }

    public void setWorkflowCurrentActivity_ApplicableTargets(java.util.Set<mxhibernate.genentities.system.DbWorkflowCurrentActivity> val) {
    }
}
