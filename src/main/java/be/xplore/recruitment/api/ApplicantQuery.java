package be.xplore.recruitment.api;

import be.xplore.recruitment.model.Applicant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Override
    public Predicate toPredicate(Root<Applicant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (isEmptyString(firstName)) {
            predicates.add(cb.like(root.get("firstName"), firstName));
        }
        if (isEmptyString(lastName)){
            predicates.add(cb.like(root.get("lastName"), lastName));
        }
        if (isEmptyString(email)){
            predicates.add(cb.like(root.get("email"), email));
        }
        if (isEmptyString(phone)){
            predicates.add(cb.like(root.get("phone"), phone));
        }
        if (isEmptyString(education)) {
            predicates.add(cb.like(root.get("education"), education));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private boolean isEmptyString(String string) {
        return StringUtils.hasText(string);
    }

}
