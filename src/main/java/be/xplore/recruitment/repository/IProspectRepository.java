package be.xplore.recruitment.repository;

import be.xplore.recruitment.model.Prospect;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */
public interface IProspectRepository extends Repository<Prospect, Long> {
    List<Prospect> mockData();

    List<Prospect> findAll();

    Prospect findProspectById(long id);

    List<Prospect> findAllByLastName(String lastName);

    List<Prospect> findAllByFirstName(String firstName);

    List<Prospect> findAllByPhone(String phone);

    List<Prospect> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Prospect> findAllByFirstNameAndEmail(String firstName, String email);
}