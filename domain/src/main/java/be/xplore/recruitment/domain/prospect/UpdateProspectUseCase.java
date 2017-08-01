package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static be.xplore.recruitment.domain.prospect.Prospect.builder;
import static be.xplore.recruitment.domain.util.Validator.isValidEmail;
import static be.xplore.recruitment.domain.util.Validator.isValidPhone;

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
        throws NotFoundException, InvalidEmailException, InvalidPhoneException {
        if (repository.findProspectById(request.prospectId) == null) {
            throw new NotFoundException();
        }
        List<ProspectResponseModel> prospectResponseModels = new ArrayList<>();
        checkEmail(request);
        checkPhone(request);

        Prospect prospect = builder(request.firstName, request.lastName)
                .withId(request.prospectId).withEmail(request.email)
                .withPhone(request.phone).build();
        prospectResponseModels.add(new ProspectResponseModel(prospect));
        repository.updateProspect(prospect);
        response.accept(prospectResponseModels);
    }

    private void checkPhone(UpdateProspectRequest request) {
        if (request.email == null || !isValidPhone(request.phone)) {
            request.phone = repository.findProspectById(request.prospectId).getPhone();
        }
    }

    private void checkEmail(UpdateProspectRequest request) {
        if (request.email == null || !isValidEmail(request.email)) {
            request.email = repository.findProspectById(request.prospectId).getEmail();
        }
    }
}
