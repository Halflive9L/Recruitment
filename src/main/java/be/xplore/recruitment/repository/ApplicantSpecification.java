package be.xplore.recruitment.repository;


import be.xplore.recruitment.domain.model.Applicant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Lander on 20/07/2017.
 */
public class ApplicantSpecification {
    public static Specification<Applicant> hasFirstName(String firstName) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("firstName")), (firstName).toLowerCase());
    }

    public static Specification<Applicant> hasLastName(String lastName) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("lastName")), (lastName).toLowerCase());
    }

    public static Specification<Applicant> hasDateOfBirth(String email) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("email")), email.toLowerCase());
    }

    public static Specification<Applicant> hasPhone(String phone) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("phone")), phone.toLowerCase());
    }

    public static Specification<Applicant> hasEducation(String education) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("education")), education.toLowerCase());
    }

    public static Specification<Applicant> hasAdress(String address) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("address")), address.toLowerCase());
    }


}
