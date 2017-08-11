package be.xplore.recruitment.domain.tag;

import java.util.Set;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public class AddAllTagsToEntityRequest {
    private long entityId;
    private Set<String> tagNames;

    public AddAllTagsToEntityRequest(long entityId, Set<String> tagNames) {
        this.entityId = entityId;
        this.tagNames = tagNames;
    }

    public long getEntityId() {
        return entityId;
    }

    public Set<String> getTagNames() {
        return tagNames;
    }
}
