/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbSynchronizationErrorFile extends mxhibernate.genentities.system.DbFileDocument
{
    public static final java.lang.String entityName = "System.SynchronizationErrorFile";

    public enum MemberNames
    {
        FileID("FileID"),
        Name("Name"),
        DeleteAfterDownload("DeleteAfterDownload"),
        Contents("Contents"),
        HasContents("HasContents"),
        Size("Size"),
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
