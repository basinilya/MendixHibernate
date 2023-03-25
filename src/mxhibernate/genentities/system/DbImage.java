/* generated with: mxhibernate.GenerateMendixJdbcProxiesFromDb */
package mxhibernate.genentities.system;

public class DbImage extends mxhibernate.genentities.system.DbFileDocument
{
    public static final java.lang.String entityName = "System.Image";

    public enum MemberNames
    {
        PublicThumbnailPath("PublicThumbnailPath"),
        Thumbnail_Image("System.Thumbnail_Image");

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
