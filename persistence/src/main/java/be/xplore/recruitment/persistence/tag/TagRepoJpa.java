package be.xplore.recruitment.persistence.tag;

import be.xplore.recruitment.domain.exception.TagAlreadyExistsException;
import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
@Repository
@Transactional
public class TagRepoJpa implements TagRepository {
    private final EntityManager entityManager;

    @Autowired
    public TagRepoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tag createTag(Tag tag) throws TagAlreadyExistsException {
        JpaTag jpaTag = new JpaTag(tag.getTagName());
        tryCreateTag(jpaTag);
        return jpaTag.toTag();
    }

    private void tryCreateTag(JpaTag tag) throws TagAlreadyExistsException {
        try {
            entityManager.persist(tag);
        } catch (EntityExistsException e) {
            throw new TagAlreadyExistsException(tag.toTag(), e);
        }
    }

    @Override
    public Optional<Tag> findTagById(long tagId) {
        JpaTag jpaTag = entityManager.find(JpaTag.class, tagId);
        return getOptionalTagFromJpaTag(jpaTag);
    }

    @Override
    public Set<Tag> findAllTags() {
        List<JpaTag> jpaTags = entityManager.createNamedQuery(JpaTag.QUERY_FIND_ALL, JpaTag.class).getResultList();
        return jpaTags.stream().map(JpaTag::toTag).collect(Collectors.toSet());
    }

    @Override
    public Optional<Tag> deleteTag(long tagId) {
        JpaTag jpaTag = entityManager.find(JpaTag.class, tagId);
        deleteTagIfNotNull(jpaTag);
        return getOptionalTagFromJpaTag(jpaTag);
    }

    private Optional<Tag> getOptionalTagFromJpaTag(JpaTag jpaTag) {
        if (jpaTag == null) {
            return Optional.empty();
        }
        return Optional.of(jpaTag.toTag());
    }

    private void deleteTagIfNotNull(JpaTag tag) {
        if (tag != null) {
            entityManager.remove(tag);
        }
    }
}