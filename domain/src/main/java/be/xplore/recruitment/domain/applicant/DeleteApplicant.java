package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public interface DeleteApplicant {
    void deleteApplicant(DeleteApplicantRequest request, DeleteApplicantResponse response) throws NotFoundException;
}
