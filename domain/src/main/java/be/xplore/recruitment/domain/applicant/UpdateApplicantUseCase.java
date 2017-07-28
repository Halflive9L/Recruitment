package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
@Named
public class UpdateApplicantUseCase implements UpdateApplicant {
    private final ApplicantRepository repository;

    public UpdateApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateApplicant(UpdateApplicantRequest request, UpdateApplicantResponse response)
            throws InvalidEmailException, InvalidPhoneException, InvalidDateException, NotFoundException {
        Applicant applicant = createApplicantFromRequest(request);
        applicant.validateApplicant();
        repository.updateApplicant(applicant);
    }

    private Applicant createApplicantFromRequest(UpdateApplicantRequest request) {
        return Applicant.builder()
                .withId(request.getApplicantId())
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
