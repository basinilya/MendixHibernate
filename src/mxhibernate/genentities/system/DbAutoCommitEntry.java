/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbAutoCommitEntry
{
    public static final java.lang.String entityName = "System.AutoCommitEntry";

    public enum MemberNames
    {
        createdDate("createdDate"),
        ObjectId("ObjectId"),
        SessionId("SessionId");

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
