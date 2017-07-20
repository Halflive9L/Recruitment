package be.xplore.recruitment.api;

import be.xplore.recruitment.model.Applicant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public class ApplicantQuery implements Specification<Applicant> {

    @Override
    public Predicate toPredicate(Root<Applicant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return null;
    }
}
