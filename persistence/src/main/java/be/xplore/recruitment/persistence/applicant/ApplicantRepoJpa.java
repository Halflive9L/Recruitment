package be.xplore.recruitment.persistence.applicant;


import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.persistence.attachment.FileManager;
import be.xplore.recruitment.persistence.attachment.JpaAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static be.xplore.recruitment.persistence.applicant.JpaApplicant.QUERY_FIND_ALL;

/**
 * @author Lander
 * @since 26/07/2017
 */
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
        JpaApplicant jpaApplicant = applicantToJpaApplicant(applicant);
        entityManager.persist(jpaApplicant);
        return jpaApplicant.toApplicant();
    }

    @Override
    public List<Applicant> findAll() {
        List<JpaApplicant> list = entityManager.createNamedQuery(QUERY_FIND_ALL, JpaApplicant.class)
                .getResultList();
        List<Applicant> result = list.stream().map(JpaApplicant::toApplicant).collect(Collectors.toList());
        return result;
    }

    @Override
    public Optional<Applicant> findApplicantById(long applicantId) {
        Applicant applicant;
        try {
            applicant = findJpaApplicantById(applicantId).toApplicant();
        } catch (NoResultException e) {
            applicant = null;
        }

        return Optional.ofNullable(applicant);
    }

    private JpaApplicant findJpaApplicantById(long applicantId) throws NoResultException {
        return (JpaApplicant) entityManager
                .createNamedQuery(JpaApplicant.QUERY_FIND_BY_ID)
                .setParameter("applicantId", applicantId).getSingleResult();
    }

    @Override
    public List<Applicant> findByParameters(Applicant applicant) {
        JpaApplicant jpaApplicant = applicantToJpaApplicant(applicant);
        CriteriaQuery<JpaApplicant> query = getJpaApplicantCriteriaQuery(jpaApplicant);
        List<JpaApplicant> results = entityManager.createQuery(query).getResultList();
        return jpaApplicantListToApplicantList(results);
    }

    private List<Applicant> jpaApplicantListToApplicantList(List<JpaApplicant> jpaApplicants) {
        List<Applicant> applicants = new ArrayList<>(jpaApplicants.size());
        jpaApplicants.forEach(a -> applicants.add(a.toApplicant()));
        return applicants;
    }

    private CriteriaQuery<JpaApplicant> getJpaApplicantCriteriaQuery(JpaApplicant jpaApplicant) {
        CriteriaQuery<JpaApplicant> query = getCriteriaBuilder().createQuery(JpaApplicant.class);
        Specification<JpaApplicant> spec = new ApplicantSpecification(jpaApplicant).getFullSpecification();
        Root<JpaApplicant> root = applySpecification(spec, query);

        query.select(root);
        return query;
    }

    private Root<JpaApplicant> applySpecification(Specification<JpaApplicant> spec, CriteriaQuery<JpaApplicant> query) {
        Root<JpaApplicant> root = query.from(JpaApplicant.class);
        Predicate predicate = spec.toPredicate(root, query, getCriteriaBuilder());
        if (predicate != null) {
            query.where(predicate);
        }
        return root;
    }

    private CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    @Override
    public Optional<Applicant> updateApplicant(Applicant applicant) {
        JpaApplicant jpaApplicant = applicantToJpaApplicant(applicant);
        jpaApplicant.setApplicantId(applicant.getApplicantId());
        try {
            applicant = entityManager.merge(jpaApplicant).toApplicant();
        } catch (IllegalArgumentException e) {
            applicant = null;
        }
        return Optional.ofNullable(applicant);
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
        JpaApplicant applicant;
        try {
            applicant = findJpaApplicantById(applicantId);
        } catch (NoResultException e) {
            throw new NotFoundException("Applicant with ID: " + applicantId + " does not exist.");
        }
        Optional<Attachment> createdAttachment = Optional.ofNullable(tryCreateAttachment(attachment));
        createdAttachment.ifPresent(a -> a.setAttachmentId(registerAttachment(applicant, a.getAttachmentName())));
        return createdAttachment;
    }

    private Attachment tryCreateAttachment(Attachment attachment) {
        try {
            attachment.setAttachmentName(fileManager.createFile(attachment.getInputStream(),
                    "applicant", attachment.getAttachmentName()));
            return attachment;
        } catch (IOException e) {
            e.printStackTrace();
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

    private JpaApplicant applicantToJpaApplicant(Applicant applicant) {
        JpaApplicant jpaApplicant = new JpaApplicant();
        jpaApplicant.setApplicantId(applicant.getApplicantId());
        jpaApplicant.setFirstName(applicant.getFirstName());
        jpaApplicant.setLastName(applicant.getLastName());
        jpaApplicant.setEmail(applicant.getEmail());
        jpaApplicant.setPhone(applicant.getPhone());
        jpaApplicant.setDateOfBirth(applicant.getDateOfBirth());
        jpaApplicant.setAddress(applicant.getAddress());
        jpaApplicant.setEducation(applicant.getEducation());
        return jpaApplicant;
    }

}