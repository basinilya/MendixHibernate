/* generated with: mxhibernate.GenerateMendixJdbcProxies */
package mxhibernate.genentities.system;

public class DbSynchronizationError
{
    public static final java.lang.String entityName = "System.SynchronizationError";

    public enum MemberNames
    {
        Reason("Reason"),
        ObjectId("ObjectId"),
        ObjectType("ObjectType"),
        ObjectContent("ObjectContent");

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

    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long val) {
        this.id = val;
    }

    private java.lang.String objectContent;

    public java.lang.String getObjectContent() {
        return this.objectContent;
    }

    public void setObjectContent(java.lang.String val) {
        this.objectContent = val;
    }

    private java.lang.String objectId;

    public java.lang.String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(java.lang.String val) {
        this.objectId = val;
    }

    private java.lang.String objectType;

    public java.lang.String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(java.lang.String val) {
        this.objectType = val;
    }

    private java.lang.String reason;

    public java.lang.String getReason() {
        return this.reason;
    }

    public void setReason(java.lang.String val) {
        this.reason = val;
    }

    private java.util.Set<mxhibernate.genentities.system.DbSynchronizationErrorFile> synchronizationErrorFile_SynchronizationError;

    public java.util.Set<mxhibernate.genentities.system.DbSynchronizationErrorFile> getSynchronizationErrorFile_SynchronizationError() {
        return this.synchronizationErrorFile_SynchronizationError;
    }

    public void setSynchronizationErrorFile_SynchronizationError(java.util.Set<mxhibernate.genentities.system.DbSynchronizationErrorFile> val) {
        this.synchronizationErrorFile_SynchronizationError = val;
    }
}
