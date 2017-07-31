package be.xplore.recruitment.domain.applicant;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public interface UpdateApplicant {
    void updateApplicant(UpdateApplicantRequest request, Consumer<List<ApplicantResponseModel>> response);
}
