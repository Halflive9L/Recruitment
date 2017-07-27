package be.xplore.recruitment.domain.applicant;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public class ReadApplicantUseCase implements ReadApplicant {
    private final ApplicantRepository repository;

    public ReadApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Applicant> readAllApplicants() {
        return repository.findAll();
    }

    @Override
    public Applicant readApplicantById(long id) {
        return repository.findApplicantById(id);
    }
}
