package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public interface DeleteApplicant {
    void deleteApplicant(DeleteApplicantRequest request, Consumer<List<ApplicantResponseModel>> response)
            throws NotFoundException;
}
