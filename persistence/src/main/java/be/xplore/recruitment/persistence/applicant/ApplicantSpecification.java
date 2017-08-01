package be.xplore.recruitment.persistence.applicant;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.sql.Date;
import java.util.Calendar;

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
        return (root, query, cb) -> cb.equal(cb.lower(root.get("firstName")), (applicant.getFirstName()).toLowerCase());
    }

    private Specification<JpaApplicant> hasLastName() {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("lastName")), (applicant.getLastName()).toLowerCase());
    }

    private Specification<JpaApplicant> hasEmail() {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("email")), applicant.getEmail().toLowerCase());
    }

    private Specification<JpaApplicant> hasPhone() {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("phone")), applicant.getPhone().toLowerCase());
    }

    private Specification<JpaApplicant> hasEducation() {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("education")), applicant.getEducation().toLowerCase());
    }

    private Specification<JpaApplicant> hasAddress() {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("address")), applicant.getAddress().toLowerCase());
    }

    private Specification<JpaApplicant> hasDateOfBirth() {
        return (root, query, cb) -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(applicant.getDateOfBirth());
            cal.add(Calendar.DATE, 1);
            Date lowDate = (Date) cal.getTime();
            cal.setTime(applicant.getDateOfBirth());
            cal.add(Calendar.DATE, -1);
            Date highDate = (Date) cal.getTime();
            return cb.between(root.get("dateOfBirth"), lowDate, highDate);
        };
    }


}
