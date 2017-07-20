package be.xplore.recruitment.repository;

import be.xplore.recruitment.domain.model.Prospect;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */


@RepositoryRestResource(collectionResourceRel = "prospects", path = "prospects")
public interface ProspectRepository extends CrudRepository<Prospect, Long>, JpaSpecificationExecutor<Prospect> {
    @Override
    List<Prospect> findAll();

    Prospect findProspectByProspectId(long prospectId);

    @Override
    List<Prospect> findAll(Specification<Prospect> spec);
}