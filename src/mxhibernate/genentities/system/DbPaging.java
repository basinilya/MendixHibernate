/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbPaging
{
    public static final java.lang.String entityName = "System.Paging";

    public enum MemberNames
    {
        PageNumber("PageNumber"),
        IsSortable("IsSortable"),
        SortAttribute("SortAttribute"),
        SortAscending("SortAscending"),
        HasMoreData("HasMoreData");

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


    private java.lang.Boolean hasMoreData;

    public java.lang.Boolean getHasMoreData() {
        return this.hasMoreData;
    }

    public void setHasMoreData(java.lang.Boolean val) {
        this.hasMoreData = val;
    }

    private java.lang.Boolean isSortable;

    public java.lang.Boolean getIsSortable() {
        return this.isSortable;
    }

    public void setIsSortable(java.lang.Boolean val) {
        this.isSortable = val;
    }

    private java.lang.Long pageNumber;

    public java.lang.Long getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(java.lang.Long val) {
        this.pageNumber = val;
    }

    private java.lang.Boolean sortAscending;

    public java.lang.Boolean getSortAscending() {
        return this.sortAscending;
    }

    public void setSortAscending(java.lang.Boolean val) {
        this.sortAscending = val;
    }

    private java.lang.String sortAttribute;

    public java.lang.String getSortAttribute() {
        return this.sortAttribute;
    }

    public void setSortAttribute(java.lang.String val) {
        this.sortAttribute = val;
    }
}
