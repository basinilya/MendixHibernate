/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbSynchronizationError
{
    public static final java.lang.String entityName = "System.SynchronizationError";

    public enum MemberNames
    {
        ObjectContent("ObjectContent"),
        ObjectType("ObjectType"),
        createdDate("createdDate"),
        ObjectId("ObjectId"),
        Reason("Reason"),
        owner("System.owner"),
        SynchronizationErrorFile_SynchronizationError("System.SynchronizationErrorFile_SynchronizationError");

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
