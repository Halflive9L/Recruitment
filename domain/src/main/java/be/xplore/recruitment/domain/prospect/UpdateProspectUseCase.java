package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

/**
 * @author Lander
 * @since 27/07/2017
 */
@Named
public class UpdateProspectUseCase implements UpdateProspect {

    private final ProspectRepository repository;

    public UpdateProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateProspect(UpdateProspectRequest request, Consumer<ProspectResponseModel> response)
            throws InvalidEmailException, InvalidPhoneException, NotFoundException {
        Prospect prospect = createProspectFromRequest(request);
        prospect.validateProspect();
        Prospect responseProspect = repository.updateProspect(prospect).orElseThrow(NotFoundException::new);
        response.accept(new ProspectResponseModel(responseProspect));
    }

    private Prospect createProspectFromRequest(UpdateProspectRequest request) {
        return Prospect.builder()
                .withFirstName(request.firstName)
                .withLastName(request.lastName)
                .withEmail(request.email)
                .withPhone(request.phone)
                .build();
    }
}
