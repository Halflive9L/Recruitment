package be.xplore.recruitment.domain.tag;

import be.xplore.recruitment.domain.exception.TagAlreadyExistsException;

import java.util.Optional;
import java.util.Set;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public interface TagRepository {
    Tag createTag(Tag tag) throws TagAlreadyExistsException;

    Optional<Tag> findTagById(long tagId);

    Set<Tag> findAllTags();

    Optional<Tag> deleteTag(long tagId);
}
