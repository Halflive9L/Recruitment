package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
@Named
public class DeleteApplicantUseCase implements DeleteApplicant {

    private ApplicantRepository repository;

    public DeleteApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteApplicant(DeleteApplicantRequest request, DeleteApplicantResponse response)
            throws NotFoundException {
        repository.deleteApplicant(request.id);
        response.onResponse(request.id);
    }
}
