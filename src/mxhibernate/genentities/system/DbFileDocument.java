/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbFileDocument
{
    public static final java.lang.String entityName = "System.FileDocument";

    public enum MemberNames
    {
        FileID("FileID"),
        Name("Name"),
        DeleteAfterDownload("DeleteAfterDownload"),
        Contents("Contents"),
        HasContents("HasContents"),
        Size("Size");

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

    private java.lang.Boolean deleteAfterDownload;

    public java.lang.Boolean getDeleteAfterDownload() {
        return this.deleteAfterDownload;
    }

    public void setDeleteAfterDownload(java.lang.Boolean val) {
        this.deleteAfterDownload = val;
    }

    private java.lang.Long fileID;

    public java.lang.Long getFileID() {
        return this.fileID;
    }

    public void setFileID(java.lang.Long val) {
        this.fileID = val;
    }

    private java.lang.Boolean hasContents;

    public java.lang.Boolean getHasContents() {
        return this.hasContents;
    }

    public void setHasContents(java.lang.Boolean val) {
        this.hasContents = val;
    }

    private java.lang.String name;

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String val) {
        this.name = val;
    }

    private java.lang.Long size;

    public java.lang.Long getSize() {
        return this.size;
    }

    public void setSize(java.lang.Long val) {
        this.size = val;
    }
}
