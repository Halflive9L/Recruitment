package be.xplore.recruitment.domain.applicant;

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
        List<ApplicantResponseModel> responseList = new LinkedList<>();
        applicants.forEach(applicant -> responseList.add(new ApplicantResponseModel(applicant)));
        response.accept(responseList);
    }

    @Override
    public void readApplicantsByParam(ReadApplicantRequest request, Consumer<List<ApplicantResponseModel>> response) {

    }

    @Override
    public void readApplicantById(ReadApplicantRequest request, Consumer<ApplicantResponseModel> consumer) {
        Applicant applicant = repository.findApplicantById(request.applicantId);
        if (applicant == null) {
            applicant = Applicant.builder().build();
        }
        ApplicantResponseModel responseModel = new ApplicantResponseModel(applicant);
        consumer.accept(responseModel);
    }
}
