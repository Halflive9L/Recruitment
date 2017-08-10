package be.xplore.recruitment.persistence.applicant;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class ApplicantSpecification {
    private final JpaApplicant applicant;
    private final EntityManager entityManager;

    public ApplicantSpecification(JpaApplicant applicant, EntityManager entityManager) {
        this.applicant = applicant;
        this.entityManager = entityManager;
    }

    public CriteriaQuery<JpaApplicant> getCriteria() {
        CriteriaQuery<JpaApplicant> query = entityManager.getCriteriaBuilder().createQuery(JpaApplicant.class);
        Specification<JpaApplicant> spec = getFullSpecification();
        Root<JpaApplicant> root = applySpecification(spec, query);
        return query.select(root);
    }

    private Root<JpaApplicant> applySpecification(Specification<JpaApplicant> spec, CriteriaQuery<JpaApplicant> query) {
        Root<JpaApplicant> root = query.from(JpaApplicant.class);
        Predicate predicate = spec.toPredicate(root, query, entityManager.getCriteriaBuilder());
        if (predicate != null) {
            query.where(predicate);
        }
        return root;
    }

    private Specification<JpaApplicant> getFullSpecification() {
        return Specifications.where(hasFirstName())
                .and(hasLastName())
                .and(hasEmail())
                .and(hasPhone())
                .and(hasEducation())
                .and(hasAddress())
                .and(hasDateOfBirth());
    }

    private Specification<JpaApplicant> hasFirstName() {
        return isStringNullOrEmpty(applicant.getFirstName()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("firstName")),
                        applicant.getFirstName().toLowerCase());
    }

    private Specification<JpaApplicant> hasLastName() {
        return isStringNullOrEmpty(applicant.getLastName()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("lastName")),
                        applicant.getLastName().toLowerCase());
    }

    private Specification<JpaApplicant> hasEmail() {
        return isStringNullOrEmpty(applicant.getEmail()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("email")),
                        applicant.getEmail().toLowerCase());
    }

    private Specification<JpaApplicant> hasPhone() {
        return isStringNullOrEmpty(applicant.getPhone()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("phone")),
                        applicant.getPhone().toLowerCase());
    }

    private Specification<JpaApplicant> hasEducation() {
        return isStringNullOrEmpty(applicant.getEducation()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("education")),
                        applicant.getEducation().toLowerCase());
    }

    private Specification<JpaApplicant> hasAddress() {
        return isStringNullOrEmpty(applicant.getAddress()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("address")),
                        applicant.getAddress().toLowerCase());
    }

    private Specification<JpaApplicant> hasDateOfBirth() {
        return applicant.getDateOfBirth() == null ? null :
                (root, query, cb) -> {
                    LocalDate lowDate = LocalDate.from(applicant.getDateOfBirth());
                    LocalDate highDate = LocalDate.from(applicant.getDateOfBirth());
                    return cb.between(root.get("dateOfBirth"), lowDate, highDate);
                };
    }

    private boolean isStringNullOrEmpty(String s) {
        return !StringUtils.hasText(s);
    }

}
