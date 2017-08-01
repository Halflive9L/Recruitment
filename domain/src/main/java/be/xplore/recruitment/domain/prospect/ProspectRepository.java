package be.xplore.recruitment.domain.prospect;

import java.util.List;
import java.util.Optional;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
public interface ProspectRepository {
    void createProspect(Prospect prospect);

    List<Prospect> findAll();

    Optional<Prospect> findProspectById(long id);

    List<Prospect> findByParameters(Prospect prospect);

    Optional<Prospect> deleteProspect(long id);

    Optional<Prospect> updateProspect(Prospect prospect);
}
