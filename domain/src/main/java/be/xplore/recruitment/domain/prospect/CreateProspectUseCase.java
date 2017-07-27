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
    public void createProspect(CreateProspectRequest request, CreateProspectResponse response) {
        Prospect prospect = new Prospect.ProspectBuilder(request.prospect.getFirstName(), request.prospect.getLastName())
                .setEmail(request.prospect.getEmail())
                .setPhone(request.prospect.getPhone()).createProspect();
        storeProspect(prospect);
        response.onResponse(prospect.getProspectId());
    }

    private void storeProspect(Prospect p) throws InvalidEmailException, InvalidPhoneException {
        p.validateProspect();
        repository.createProspect(p);
    }
}