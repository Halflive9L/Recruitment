package be.xplore.recruitment.domain.prospect;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadProspect {
    List<Prospect> readAllProspects();

    Prospect readProspectById(long id);
}
