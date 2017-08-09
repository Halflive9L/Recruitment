package be.xplore.recruitment.persistence.prospect;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.StringUtils;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public class ProspectSpecification {


    private JpaProspect prospect;

    public ProspectSpecification(JpaProspect prospect) {
        this.prospect = prospect;
    }

    Specification<JpaProspect> getFullSpecification() {
        return Specifications.where(hasFirstName())
                .and(hasLastName())
                .and(hasEmail())
                .and(hasPhone());
    }


    private Specification<JpaProspect> hasFirstName() {
        return isStringNullOrEmpty(prospect.getFirstName()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("firstName")),
                        prospect.getFirstName().toLowerCase());
    }

    private Specification<JpaProspect> hasLastName() {
        return isStringNullOrEmpty(prospect.getLastName()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("lastName")),
                        prospect.getLastName().toLowerCase());
    }

    private Specification<JpaProspect> hasEmail() {
        return isStringNullOrEmpty(prospect.getEmail()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("email")),
                        prospect.getEmail().toLowerCase());
    }

    private Specification<JpaProspect> hasPhone() {
        return isStringNullOrEmpty(prospect.getPhone()) ? null :
                (root, query, cb) -> cb.equal(cb.lower(root.get("phone")),
                        prospect.getPhone().toLowerCase());
    }

    private boolean isStringNullOrEmpty(String s) {
        return !StringUtils.hasText(s);
    }

}