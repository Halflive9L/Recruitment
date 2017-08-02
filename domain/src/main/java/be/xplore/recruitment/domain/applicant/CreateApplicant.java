package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface CreateApplicant {
    void createApplicant(CreateApplicantRequest request, Consumer<ApplicantResponseModel> response)
            throws InvalidEmailException, InvalidPhoneException, InvalidDateException;
}
