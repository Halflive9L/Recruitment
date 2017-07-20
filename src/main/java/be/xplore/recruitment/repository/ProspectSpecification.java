package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public class ProspectSpecification {
    //TODO: Stijn
    public static Specification<Prospect> hasFirstName(String firstName) {
        return null;
    }

    //TODO: Stijn
    public static Specification<Prospect> hasLastName(String lastName) {
        return null;
    }

    //TODO: Lander
    public static Specification<Prospect> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    //TODO: Lander
    public static Specification<Prospect> hasPhone(String phone) {
        return (root, query, cb) -> cb.equal(root.get("phone"), phone);
    }
}
