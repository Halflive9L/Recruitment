package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;
import org.springframework.data.jpa.domain.Specification;

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
        return null;
    }

    //TODO: Lander
    public static Specification<Prospect> hasPhone(String phone) {
        return null;
    }
}
