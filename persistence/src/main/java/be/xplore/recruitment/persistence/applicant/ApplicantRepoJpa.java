package be.xplore.recruitment.persistence.applicant;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.EntityAlreadyHasTagException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.persistence.attachment.FileManager;
import be.xplore.recruitment.persistence.attachment.JpaAttachment;
import be.xplore.recruitment.persistence.tag.JpaTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static be.xplore.recruitment.persistence.applicant.JpaApplicant.QUERY_FIND_ALL;

@Repository
@Transactional
public class ApplicantRepoJpa implements ApplicantRepository {
    private final EntityManager entityManager;
    private final FileManager fileManager;

    @Autowired
    public ApplicantRepoJpa(EntityManager entityManager, FileManager fileManager) {
        this.entityManager = entityManager;
        this.fileManager = fileManager;
    }

    @Override
    public Applicant createApplicant(Applicant applicant) {
        JpaApplicant jpaApplicant = JpaApplicant.fromApplicant(applicant);
        entityManager.persist(jpaApplicant);
        return jpaApplicant.toApplicant();
    }

    @Override
    public List<Applicant> findAll() {
        List<JpaApplicant> list = entityManager.createNamedQuery(QUERY_FIND_ALL, JpaApplicant.class).getResultList();
        return list.stream().map(JpaApplicant::toApplicant).collect(Collectors.toList());
    }

    @Override
    public Optional<Applicant> findApplicantById(long applicantId) {
        try {
            return Optional.of(findJpaApplicantById(applicantId).toApplicant());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    private JpaApplicant findJpaApplicantById(long applicantId) throws NoResultException {
        return (JpaApplicant) entityManager
                .createNamedQuery(JpaApplicant.QUERY_FIND_BY_ID)
                .setParameter("applicantId", applicantId).getSingleResult();
    }

    @Override
    public List<Applicant> findByParameters(Applicant applicant) {
        JpaApplicant jpaApplicant = JpaApplicant.fromApplicant(applicant);
        CriteriaQuery<JpaApplicant> query = new ApplicantSpecification(jpaApplicant, entityManager).getCriteria();
        List<JpaApplicant> results = entityManager.createQuery(query).getResultList();
        return results.stream().map(JpaApplicant::toApplicant).collect(Collectors.toList());
    }

    @Override
    public Optional<Applicant> updateApplicant(Applicant applicant) {
        JpaApplicant jpaApplicant = JpaApplicant.fromApplicant(applicant);
        return getOptionalApplicant(applicant, jpaApplicant);
    }

    private Optional<Applicant> getOptionalApplicant(Applicant applicant, JpaApplicant jpaApplicant) {
        try {
            copyTags(applicant.getApplicantId(), jpaApplicant);
            return Optional.of(entityManager.merge(jpaApplicant).toApplicant());
        } catch (IllegalArgumentException | NoResultException e) {
            return Optional.empty();
        }
    }

    private void copyTags(long copyFromId, JpaApplicant copyTo) throws NoResultException {
        JpaApplicant original = findJpaApplicantById(copyFromId);
        copyTo.setTags(original.getTags());
    }

    @Override
    public Optional<Applicant> deleteApplicant(long applicantId) {
        List<JpaApplicant> applicantList = entityManager
                .createNamedQuery(JpaApplicant.QUERY_FIND_BY_ID, JpaApplicant.class)
                .setParameter("applicantId", applicantId).getResultList();
        entityManager.createNamedQuery(JpaApplicant.QUERY_DELETE).setParameter("applicantId", applicantId)
                .executeUpdate();
        return Optional.ofNullable(applicantList.get(0).toApplicant());
    }

    @Override
    public Optional<Attachment> addAttachment(long applicantId, Attachment attachment)
            throws NotFoundException {
        JpaApplicant applicant = tryFindById(applicantId);
        Optional<Attachment> createdAttachment = Optional.ofNullable(tryCreateAttachment(attachment));
        createdAttachment.ifPresent(a -> a.setAttachmentId(registerAttachment(applicant, a.getAttachmentName())));
        return createdAttachment;
    }

    private JpaApplicant tryFindById(long applicantId) {
        JpaApplicant applicant;
        try {
            applicant = findJpaApplicantById(applicantId);
        } catch (NoResultException e) {
            throw new NotFoundException("Applicant with ID: " + applicantId + " does not exist.");
        }
        return applicant;
    }

    private Attachment tryCreateAttachment(Attachment attachment) {
        try {
            attachment.setAttachmentName(fileManager.createFile(attachment.getInputStream(),
                                                                "applicant", attachment.getAttachmentName()));
            return attachment;
        } catch (IOException e) {
            return null;
        }
    }

    private long registerAttachment(JpaApplicant applicant, String fileName) {
        JpaAttachment jpaAttachment = new JpaAttachment(applicant, fileName);
        entityManager.persist(jpaAttachment);
        return jpaAttachment.getAttachmentId();
    }

    @Override
    public List<Attachment> findAllAttachmentsForApplicant(long applicantId) {
        Set<JpaAttachment> jpaAttachments = getAttachmentSetFromApplicantId(applicantId);
        List<Attachment> attachments = new ArrayList<>(jpaAttachments.size());
        jpaAttachments.forEach(jpaAttachment -> attachments.add(jpaAttachment.toAttachment()));
        return attachments;
    }

    private Set<JpaAttachment> getAttachmentSetFromApplicantId(long applicantId) {
        JpaApplicant jpaApplicant = findJpaApplicantById(applicantId);
        return jpaApplicant.getAttachments();
    }

    @Override
    public Tag addTagToApplicant(long applicantId, Tag tag) throws EntityAlreadyHasTagException {
        JpaApplicant applicant = findJpaApplicantById(applicantId);
        throwExceptionIfApplicantHasTag(applicant, tag);
        entityManager.merge(applicant);
        return tag;
    }

    private void throwExceptionIfApplicantHasTag(JpaApplicant applicant, Tag tag) throws EntityAlreadyHasTagException {
        if (!applicant.getTags().add(new JpaTag(tag.getTagId(), tag.getTagName()))) {
            throw new EntityAlreadyHasTagException(Applicant.class, tag);
        }
    }

    @Override
    public Set<Tag> addAllTagsToApplicant(long applicantId, Set<Tag> tags)
            throws NoResultException {
        JpaApplicant applicant = findJpaApplicantById(applicantId);
        applicant.getTags().addAll(tags.stream().map(JpaTag::fromTag).collect(Collectors.toSet()));
        entityManager.merge(applicant);
        return tags;
    }
}