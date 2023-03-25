/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbBackgroundJob
{
    public static final java.lang.String entityName = "System.BackgroundJob";

    public enum MemberNames
    {
        EndTime("EndTime"),
        StartTime("StartTime"),
        Successful("Successful"),
        JobId("JobId"),
        Result("Result"),
        BackgroundJob_XASInstance("System.BackgroundJob_XASInstance"),
        BackgroundJob_Session("System.BackgroundJob_Session");

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
