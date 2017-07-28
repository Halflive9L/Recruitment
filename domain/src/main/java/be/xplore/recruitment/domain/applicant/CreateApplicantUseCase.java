package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
class CreateApplicantUseCase implements CreateApplicant {
    private final ApplicantRepository repository;

    CreateApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createApplicant(CreateApplicantRequest request, CreateApplicantResponse response)
            throws InvalidEmailException, InvalidPhoneException, InvalidDateException {
        Applicant a = createApplicantFromRequest(request);
        a.validateApplicant();
        repository.createApplicant(a);
        response.onResponse(a.getApplicantId());
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