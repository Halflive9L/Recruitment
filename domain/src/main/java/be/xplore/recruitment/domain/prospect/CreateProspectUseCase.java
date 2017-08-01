package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
@Named
public class CreateProspectUseCase implements CreateProspect {

    private final ProspectRepository repository;

    public CreateProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createProspect(CreateProspectRequest request, Consumer<List<ProspectResponseModel>> response)
            throws InvalidEmailException, InvalidPhoneException {
        List<ProspectResponseModel> prospectResponseModels = new ArrayList<>();
        Prospect prospect = createProspectFromRequest(request);
        prospect.validateProspect();
        prospectResponseModels.add(new ProspectResponseModel(prospect));
        repository.createProspect(prospect);
        response.accept(prospectResponseModels);
    }

    private Prospect createProspectFromRequest(CreateProspectRequest request) {
        return Prospect.builder(request.firstName, request.lastName)
                .withEmail(request.email)
                .withPhone(request.phone)
                .withId(request.prospectId).build();
    }
}