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
    public void readAllApplicants(ReadAllApplicantsResponse response) {
        List<Applicant> applicants = repository.findAll();
        response.onResponse(applicants);
    }

    @Override
    public void readApplicantsByParam(ReadApplicantRequest request, ReadApplicantsByParamResponse response) {

    }

    @Override
    public void readApplicantById(ReadApplicantRequest request, ReadApplicantByIdResponse response) {

    }
}
