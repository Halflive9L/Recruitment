package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public class RemoveTagFromEntityRequest {
    private long entityId;
    private String tag;
    public RemoveTagFromEntityRequest(long entityId, String tag) {
        this.entityId = entityId;
        this.tag = tag;
    }

    public long getEntityId() {
        return entityId;
    }

    public String getTag() {
        return tag;
    }
}
