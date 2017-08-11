package be.xplore.recruitment.domain.tag;

import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;

import java.util.Optional;
import java.util.Set;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
public interface TagRepository {
    Tag createTag(String tagName) throws EntityAlreadyHasTagException;

    Optional<Tag> findTagById(long tagId);

    Optional<Tag> findTagByName(String tagName);

    Set<Tag> findAllTags();

    Optional<Tag> deleteTag(long tagId);
}
