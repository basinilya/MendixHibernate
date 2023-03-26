/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbImage extends mxhibernate.genentities.system.DbFileDocument
{
    public static final java.lang.String entityName = "System.Image";

    public enum MemberNames
    {
        PublicThumbnailPath("PublicThumbnailPath"),
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

    private java.lang.String publicThumbnailPath;

    public java.lang.String getPublicThumbnailPath() {
        return this.publicThumbnailPath;
    }

    public void setPublicThumbnailPath(java.lang.String val) {
        this.publicThumbnailPath = val;
    }
}
