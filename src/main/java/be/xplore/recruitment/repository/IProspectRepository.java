package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    Prospect findProspectByProspectId(@Param("prospectId") long prospectId);

    List<Prospect> findAllByLastName(@Param("lastName") String lastName);

    List<Prospect> findAllByFirstName(@Param("firstName") String firstName);

    List<Prospect> findAllByPhone(@Param("phone") String phone);

    List<Prospect> findAllByEmail(@Param("email") String email);

    List<Prospect> findAllByFirstNameAndLastName(@Param("firstName") String firstName,@Param("lastName") String lastName);


}