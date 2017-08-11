package be.xplore.recruitment.domain.tag;

import java.util.Collections;
import java.util.Set;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public class TagSetResponseModel {
    private Set<Tag> tags;

    public TagSetResponseModel(Set<Tag> tags) {
        this.tags = Collections.unmodifiableSet(tags);
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
