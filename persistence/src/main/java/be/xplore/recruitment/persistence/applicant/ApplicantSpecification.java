package be.xplore.recruitment.persistence.applicant;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lander on 20/07/2017.
 */
public class ApplicantSpecification {
    private JpaApplicant applicant;

    public ApplicantSpecification(JpaApplicant applicant) {
        this.applicant = applicant;
    }

    Specification<JpaApplicant> getFullSpecification() {
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
                        (applicant.getFirstName()).toLowerCase());
    }

    private Specification<JpaApplicant> hasLastName() {
        return isStringNullOrEmpty(applicant.getLastName()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("lastName")),
                        (applicant.getLastName()).toLowerCase());
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
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(applicant.getDateOfBirth());
                    cal.add(Calendar.DATE, 1);
                    Date lowDate = cal.getTime();
                    cal.setTime(applicant.getDateOfBirth());
                    cal.add(Calendar.DATE, -1);
                    Date highDate = cal.getTime();
                    return cb.between(root.get("dateOfBirth"), lowDate, highDate);
                };
    }

    private boolean isStringNullOrEmpty(String s) {
        return !StringUtils.hasText(s);
    }

}
