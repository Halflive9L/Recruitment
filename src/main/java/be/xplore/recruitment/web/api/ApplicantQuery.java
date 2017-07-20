package be.xplore.recruitment.web.api;

import be.xplore.recruitment.domain.model.Applicant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public class ApplicantQuery implements Specification<Applicant> {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String education;
    private String address;
    private Date dateOfBirth;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Predicate toPredicate(Root<Applicant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (isNotEmptyString(firstName)) {
            predicates.add(cb.like(root.get("firstName"), firstName));
        }
        if (isNotEmptyString(lastName)){
            predicates.add(cb.like(root.get("lastName"), lastName));
        }
        if (isNotEmptyString(email)){
            predicates.add(cb.like(root.get("email"), email));
        }
        if (isNotEmptyString(phone)){
            predicates.add(cb.like(root.get("phone"), phone));
        }
        if (isNotEmptyString(education)) {
            predicates.add(cb.like(root.get("education"), education));
        }
        if (isNotEmptyString(address)) {
            predicates.add(cb.like(root.get("address"), address));
        }
        if (dateOfBirth != null) {
            predicates.add(cb.like(cb.toString(root.get("dateOfBirth")), dateOfBirth.toString()));
        } else {
            System.out.println("Date:" + dateOfBirth);
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private boolean isNotEmptyString(String string) {
        return StringUtils.hasText(string);
    }

    @Override
    public String toString() {
        return "ApplicantQuery{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", education='" + education + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
