package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
public class CreateProspectUseCase implements CreateProspect {
    private final ProspectRepository repository;

    CreateProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createProspect(CreateProspectRequest request, CreateProspectResponse response)
            throws InvalidEmailException, InvalidPhoneException {
        request.prospect.validateProspect();
        repository.createProspect(request.prospect);
        response.onResponse(request.prospect.getProspectId());
    }
}