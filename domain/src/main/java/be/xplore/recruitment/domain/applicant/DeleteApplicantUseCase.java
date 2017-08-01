package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    public void deleteApplicant(DeleteApplicantRequest request, Consumer<List<ApplicantResponseModel>> response)
            throws NotFoundException {
        Applicant applicant = repository.deleteApplicant(request.id).orElseThrow(NotFoundException::new);
        List<ApplicantResponseModel> responseModel = new ArrayList<>(1);
        responseModel.add(new ApplicantResponseModel(applicant));
        response.accept(responseModel);
    }
}