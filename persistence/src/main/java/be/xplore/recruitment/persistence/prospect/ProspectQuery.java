package be.xplore.recruitment.persistence.prospect;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProspectQuery implements Specification<JpaProspect> {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Predicate toPredicate(Root<JpaProspect> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (isEmptyString(firstName)) {
            predicates.add(cb.like(root.get("firstName"), firstName));
        }
        if (isEmptyString(lastName)) {
            predicates.add(cb.like(root.get("lastName"), lastName));
        }
        if (isEmptyString(email)) {
            predicates.add(cb.like(root.get("email"), email));
        }
        if (isEmptyString(phone)) {
            predicates.add(cb.like(root.get("phone"), phone));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private boolean isEmptyString(String string) {
        return StringUtils.hasText(string);
    }

    @Override
    public String toString() {
        return "ProspectQuery{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}