package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@Named
public class ReadApplicantUseCase implements ReadApplicant {
    private final ApplicantRepository repository;

    ReadApplicantUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }


    @Override
    public void readAllApplicants(Consumer<List<ApplicantResponseModel>> response) {
        List<Applicant> applicants = repository.findAll();
        List<ApplicantResponseModel> responseList = getResponseListFromApplicantList(applicants);
        response.accept(responseList);
    }

    @Override
    public void readApplicantsByParam(ReadApplicantRequest request, Consumer<List<ApplicantResponseModel>> response)
            throws NotFoundException {
        List<Applicant> applicants = repository.findByParameters(request.toApplicant());
        if (applicants.isEmpty()) {
            throw new NotFoundException();
        }
        List<ApplicantResponseModel> responseList = getResponseListFromApplicantList(applicants);
        response.accept(responseList);
    }

    private List<ApplicantResponseModel> getResponseListFromApplicantList(List<Applicant> applicants) {
        List<ApplicantResponseModel> responseList = new LinkedList<>();
        applicants.forEach(applicant -> responseList.add(new ApplicantResponseModel(applicant)));
        return responseList;
    }

    @Override
    public void readApplicantById(ReadApplicantRequest request, Consumer<ApplicantResponseModel> consumer)
            throws NotFoundException {
        Applicant applicant = repository.findApplicantById(request.applicantId).orElseThrow(NotFoundException::new);
        consumer.accept(new ApplicantResponseModel(applicant));
    }
}
