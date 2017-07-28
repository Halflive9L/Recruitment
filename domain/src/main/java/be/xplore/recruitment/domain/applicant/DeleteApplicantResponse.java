package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public interface DeleteApplicantResponse {
    void onResponse(long applicantId);
}
