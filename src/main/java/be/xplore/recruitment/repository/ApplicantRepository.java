package be.xplore.recruitment.repository;

import be.xplore.recruitment.domain.model.Applicant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public interface ApplicantRepository extends CrudRepository<Applicant, Long>, JpaSpecificationExecutor<Applicant> {
    @Override
    List<Applicant> findAll();

    @Override
    List<Applicant> findAll(Specification<Applicant> spec);
}
