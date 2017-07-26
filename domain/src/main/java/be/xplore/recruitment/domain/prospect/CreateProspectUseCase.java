package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;

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
    public void createProspect(CreateProspectRequest request, CreateProspectResponse output) throws NotFoundException {

        Prospect prospect = new Prospect(request.prospect.getFirstName(),
                request.prospect.getLastName(), request.prospect.getEmail(),
                request.prospect.getPhone());

        storeProspect(prospect);

        output.onResponse(prospect.getProspectId());
    }

    public void storeProspect(Prospect p) throws InvalidEmailException, InvalidPhoneException {
        p.validateProspect();
        repository.createProspect(p);
    }
}