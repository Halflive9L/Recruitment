package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class DeleteApplicantUseCase implements DeleteApplicant {

    private ApplicantRepository repository;

    public DeleteApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteApplicant(long id) throws NotFoundException {
        repository.deleteApplicant(id);
    }
}
