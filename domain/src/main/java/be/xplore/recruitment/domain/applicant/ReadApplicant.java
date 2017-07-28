package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadApplicant {
    void readAllApplicants(ReadAllApplicantsResponse response) throws NotFoundException;

    void readApplicantsByParam(ReadApplicantRequest request, ReadApplicantsByParamResponse response)
            throws NotFoundException;

    void readApplicantById(ReadApplicantRequest request, Consumer<ApplicantResponseModel> response);
}