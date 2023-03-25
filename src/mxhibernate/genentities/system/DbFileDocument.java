/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbFileDocument
{
    public static final java.lang.String entityName = "System.FileDocument";

    public enum MemberNames
    {
        __UUID__("__UUID__"),
        changedDate("changedDate"),
        DeleteAfterDownload("DeleteAfterDownload"),
        createdDate("createdDate"),
        Contents("Contents"),
        Size("Size"),
        HasContents("HasContents"),
        FileID("FileID"),
        __FileName__("__FileName__"),
        submetaobjectname("submetaobjectname"),
        Name("Name"),
        owner("System.owner"),
        changedBy("System.changedBy");

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
