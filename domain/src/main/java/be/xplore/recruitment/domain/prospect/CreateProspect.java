package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
public interface CreateProspect {
 //   void createProspect(Prospect prospect);
    void createProspect(CreateProspectRequest request, CreateProspectResponse response) throws NotFoundException;
}
