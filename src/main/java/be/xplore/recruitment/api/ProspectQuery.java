package be.xplore.recruitment.api;

import be.xplore.recruitment.model.Prospect;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProspectQuery implements Specification<Prospect> {
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
    public Predicate toPredicate(Root<Prospect> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null) {
            predicates.add(cb.like(root.get("firstName"), firstName));
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
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
