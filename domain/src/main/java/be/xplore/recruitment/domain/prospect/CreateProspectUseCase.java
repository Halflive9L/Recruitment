package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
class CreateProspectUseCase implements CreateProspect {
    private final ProspectRepository repository;

    CreateProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createProspect(Prospect p) throws InvalidEmailException, InvalidPhoneException {
        p.validateProspect();
        repository.createProspect(p);
    }
}