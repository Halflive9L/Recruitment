package be.xplore.recruitment.persistence.applicant;


import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.persistence.file.FileManager;
import be.xplore.recruitment.persistence.file.JpaAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static be.xplore.recruitment.persistence.applicant.JpaApplicant.QUERY_FIND_ALL;

/**
 * @author Lander
 * @since 26/07/2017
 */
@Component
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
    public void createApplicant(Applicant applicant) {
        JpaApplicant jpaApplicant = applicantToJpaApplicant(applicant);
        entityManager.persist(jpaApplicant);
    }

    @Override
    public List<Applicant> findAll() {
        List<JpaApplicant> list = entityManager.createNamedQuery(QUERY_FIND_ALL)
                .getResultList();
        List<Applicant> result = list.stream().map(JpaApplicant::toApplicant).collect(Collectors.toList());
        return result;
    }

    @Override
    public Optional<Applicant> findApplicantById(long applicantId) {
        Applicant applicant;
        try {
            JpaApplicant jpaApplicant = (JpaApplicant) entityManager
                    .createNamedQuery(JpaApplicant.QUERY_FIND_BY_ID)
                    .setParameter("applicantId", applicantId).getSingleResult();
            applicant = jpaApplicant.toApplicant();
        } catch (NoResultException e) {
            applicant = null;
        }

        return Optional.ofNullable(applicant);
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
        Applicant applicantToReturn;
        try {
            applicantToReturn = entityManager.merge(jpaApplicant).toApplicant();
        } catch (IllegalArgumentException e) {
            applicantToReturn = null;
        }
        return Optional.ofNullable(applicantToReturn);
    }

    @Override
    public Optional<Applicant> deleteApplicant(long applicantId) {
        List applicantList = entityManager.createNamedQuery(JpaApplicant.QUERY_FIND_BY_ID)
                .setParameter("applicantId", applicantId).getResultList();
        entityManager.createNamedQuery(JpaApplicant.QUERY_DELETE).setParameter("applicantId", applicantId)
                .executeUpdate();
        return Optional.ofNullable(((JpaApplicant) applicantList.get(0)).toApplicant());
    }

    @Override
    public Optional<String> addAttachment(long applicantId, String fileName, InputStream input)
            throws NotFoundException {
        Optional<Applicant> applicant = findApplicantById(applicantId);
        if (!applicant.isPresent()) {
            throw new NotFoundException("Applicant with ID: " + applicantId + " does not exist.");
        }
        Optional<String> createdFileName = Optional.ofNullable(tryCreateAttachment(input, fileName));
        createdFileName.ifPresent(s -> registerAttachment(applicant.get(), s));
        return createdFileName;
    }

    private String tryCreateAttachment(InputStream input, String fileName) {
        try {
            return fileManager.createFile(input, "applicant", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void registerAttachment(Applicant applicant, String fileName) {
        JpaApplicant jpaApplicant = applicantToJpaApplicant(applicant);
        JpaAttachment attachment = new JpaAttachment(jpaApplicant, fileName);
        entityManager.persist(attachment);
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