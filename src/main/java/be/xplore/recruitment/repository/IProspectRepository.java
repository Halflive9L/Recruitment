package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */


@RepositoryRestResource(collectionResourceRel = "prospects", path = "prospects")
public interface IProspectRepository extends CrudRepository<Prospect, Long> {
    /*List<Prospect> mockData();*/

    List<Prospect> findAll();

    Prospect findProspectByProspectId(long prospectId);

    List<Prospect> findAllByLastName(String lastName);

    List<Prospect> findAllByFirstName(String firstName);

    List<Prospect> findAllByPhone(String phone);

    List<Prospect> findAllByEmail(String email);

}