package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
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
    public void updateProspect(UpdateProspectRequest request, Consumer<List<ProspectResponseModel>> response)
            throws InvalidEmailException, InvalidPhoneException, NotFoundException {
        Prospect prospect = createProspectFromRequest(request);
        prospect.validateProspect();
        Prospect responseProspect = repository.updateProspect(prospect).orElseThrow(NotFoundException::new);
        List<ProspectResponseModel> responseModel = new ArrayList<>(1);
        responseModel.add(new ProspectResponseModel(responseProspect));
        response.accept(responseModel);
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
