package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;
import be.xplore.recruitment.domain.tag.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
public interface ProspectRepository {
    Prospect createProspect(Prospect prospect);

    List<Prospect> findAll();

    Optional<Prospect> findProspectById(long id);

    List<Prospect> findByParameters(Prospect prospect);

    Optional<Prospect> deleteProspect(long id);

    Optional<Prospect> updateProspect(Prospect prospect);

    Tag addTagToProspect(long prospectId, Tag tag) throws EntityAlreadyHasTagException;

    Set<Tag> addAllTagsToProspect(long prospectId, Set<Tag> tags);
}
