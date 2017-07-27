package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public class ReadApplicantUseCase implements ReadApplicant {
    private final ApplicantRepository repository;

    ReadApplicantUseCase(ApplicantRepository repository) {
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
        Applicant applicant = repository.findApplicantById(request.applicantId);
        if (applicant == null) {
            throw new NotFoundException();
        }
        response.onResponse(applicant);
    }
}
