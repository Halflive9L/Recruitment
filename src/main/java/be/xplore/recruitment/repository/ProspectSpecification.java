package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public class ProspectSpecification {
    public static Specification<Prospect> hasFirstName(String firstName) {
        return (root, query, cb) -> cb.equal(root.get("firstName"), (firstName));
    }

    public static Specification<Prospect> hasLastName(String lastName) {
        return (root, query, cb) -> cb.equal(root.get("lastName"), (lastName));
    }

    public static Specification<Prospect> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<Prospect> hasPhone(String phone) {
        return (root, query, cb) -> cb.equal(root.get("phone"), phone);
    }
}