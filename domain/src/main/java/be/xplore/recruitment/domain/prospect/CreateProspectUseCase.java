package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import javax.inject.Named;
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
    public void createProspect(CreateProspectRequest request, Consumer<ProspectResponseModel> response)
            throws InvalidEmailException, InvalidPhoneException {
        Prospect prospect = createProspectFromRequest(request);
        prospect.validateProspect();
        prospect = repository.createProspect(prospect);
        response.accept(new ProspectResponseModel(prospect));
    }

    private Prospect createProspectFromRequest(CreateProspectRequest request) {
        return Prospect.builder()
                .withFirstName(request.firstName)
                .withLastName(request.lastName)
                .withEmail(request.email)
                .withPhone(request.phone)
                .withProspectId(request.prospectId)
                .build();
    }
}