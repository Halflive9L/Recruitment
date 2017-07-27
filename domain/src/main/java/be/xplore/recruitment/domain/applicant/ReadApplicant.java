package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadApplicant {
    void readAllApplicants(ReadAllApplicantsResponse response);

    void readApplicantsByParam(ReadApplicantRequest request, ReadApplicantsByParamResponse response);

    void readApplicantById(ReadApplicantRequest request, ReadApplicantByIdResponse response);
}
