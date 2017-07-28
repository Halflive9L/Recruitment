package be.xplore.recruitment.domain.prospect;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
public interface ProspectRepository {
    void createProspect(Prospect prospect);

    List<Prospect> findAll();

    Prospect findProspectById(long id);

    void deleteProspect(long id);

    Prospect updateProspect(Prospect prospect);
}
