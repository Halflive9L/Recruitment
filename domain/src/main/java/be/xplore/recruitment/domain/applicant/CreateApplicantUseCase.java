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
        request.applicant.validateApplicant();
        repository.createApplicant(request.applicant);
        response.onResponse(request.applicant.getApplicantId());
    }
}