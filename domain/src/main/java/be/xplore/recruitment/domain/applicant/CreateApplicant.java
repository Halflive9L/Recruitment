package be.xplore.recruitment.domain.applicant;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface CreateApplicant {
    void createApplicant(CreateApplicantRequest request, Consumer<ApplicantResponseModel> response);
}
