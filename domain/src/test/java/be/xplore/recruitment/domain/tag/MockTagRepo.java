package be.xplore.recruitment.domain.tag;

import be.xplore.recruitment.domain.exception.TagAlreadyExistsException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public class MockTagRepo implements TagRepository {
    private Set<Tag> tags;

    MockTagRepo() {
        this.tags = new HashSet<>();
        tags.add(new Tag(1, "testTag1"));
        tags.add(new Tag(2, "testTag2"));
    }

    @Override
    public Tag createTag(Tag tag) throws TagAlreadyExistsException {
        tag.setTagId(tags.size() + 1);
        if (tags.add(tag)) {
            return tag;
        }
        throw new TagAlreadyExistsException(tag);
    }

    @Override
    public Optional<Tag> findTagById(long tagId) {
        return null;
    }

    @Override
    public Set<Tag> findAllTags() {
        return null;
    }

    @Override
    public Optional<Tag> deleteTag(long tagId) {
        return null;
    }
}
