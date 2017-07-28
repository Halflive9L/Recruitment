package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadApplicant {
    void readAllApplicants(ReadAllApplicantsResponse response) throws NotFoundException;

    void readApplicantsByParam(ReadApplicantRequest request, ReadApplicantsByParamResponse response)
            throws NotFoundException;

    void readApplicantById(ReadApplicantRequest request, ReadApplicantByIdResponse response)
            throws NotFoundException;
}
