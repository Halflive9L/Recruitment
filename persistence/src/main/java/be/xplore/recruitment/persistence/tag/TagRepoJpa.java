package be.xplore.recruitment.persistence.tag;

import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public Tag createTag(String tagName) {
        JpaTag jpaTag = new JpaTag(tagName);
        entityManager.persist(jpaTag);
        return jpaTag.toTag();
    }

    @Override
    public Optional<Tag> findTagById(long tagId) {
        JpaTag jpaTag = entityManager.find(JpaTag.class, tagId);
        return getOptionalTagFromJpaTag(jpaTag);
    }

    @Override
    public Optional<Tag> findTagByName(String tagName) {
        try {
            JpaTag jpaTag = (JpaTag) entityManager.createNamedQuery(JpaTag.QUERY_FIND_BY_NAME)
                    .setParameter("tagName", tagName).getSingleResult();
            return Optional.of(jpaTag.toTag());
        } catch (NoResultException e) {
            return Optional.empty();
        }
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