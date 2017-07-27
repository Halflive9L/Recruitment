package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public interface ReadApplicantByIdResponse {
    void onResponse(Applicant applicant);
}
