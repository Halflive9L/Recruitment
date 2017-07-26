package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
class CreateApplicantUseCase {
    private final ApplicantRepository repository;

    CreateApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }

    void createApplicant(Applicant a) throws InvalidEmailException, InvalidPhoneException {
        a.validateApplicant();
        repository.createApplicant(a);
    }
}
