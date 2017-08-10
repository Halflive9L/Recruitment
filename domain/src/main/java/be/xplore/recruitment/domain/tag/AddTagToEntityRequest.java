package be.xplore.recruitment.domain.tag;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class AddTagToEntityRequest {
    private long entityId;
    private String tagName;

    public AddTagToEntityRequest(long entityId, String tagName) {
        this.entityId = entityId;
        this.tagName = tagName;
    }

    public long getEntityId() {
        return entityId;
    }

    public String getTagName() {
        return tagName;
    }
}
