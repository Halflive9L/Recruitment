package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadApplicant {
    void readAllApplicants(Consumer<List<ApplicantResponseModel>> response);

    void readApplicantsByParam(ReadApplicantRequest request, Consumer<List<ApplicantResponseModel>> response)
            throws NotFoundException;

    void readApplicantById(ReadApplicantRequest request, Consumer<ApplicantResponseModel> response)
            throws NotFoundException;
}