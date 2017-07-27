package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface CreateApplicant {
    void createApplicant(CreateApplicantRequest request, CreateApplicantResponse response);
}
