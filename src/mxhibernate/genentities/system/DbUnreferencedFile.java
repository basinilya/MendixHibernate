/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbUnreferencedFile
{
    public static final java.lang.String entityName = "System.UnreferencedFile";

    public enum MemberNames
    {
        FileKey("FileKey"),
        createdDate("createdDate"),
        State("State"),
        TransactionId("TransactionId"),
        UnreferencedFile_XASInstance("System.UnreferencedFile_XASInstance");

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
