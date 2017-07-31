package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@Named
class CreateApplicantUseCase implements CreateApplicant {
    private final ApplicantRepository repository;

    CreateApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createApplicant(CreateApplicantRequest request, Consumer<List<ApplicantResponseModel>> response)
            throws InvalidEmailException, InvalidPhoneException, InvalidDateException {
        Applicant applicant = createApplicantFromRequest(request);
        applicant.validateApplicant();
        repository.createApplicant(applicant);
        List<ApplicantResponseModel> responseModel = new ArrayList<>(1);
        responseModel.add(new ApplicantResponseModel(applicant));
        response.accept(responseModel);
    }

    private Applicant createApplicantFromRequest(CreateApplicantRequest request) {
        return Applicant.builder()
                .withFirstName(request.firstName)
                .withLastName(request.lastName)
                .withAddress(request.address)
                .withDateOfBirth(request.dateOfBirth)
                .withEducation(request.education)
                .withEmail(request.email)
                .withPhone(request.phone)
                .build();
    }
}