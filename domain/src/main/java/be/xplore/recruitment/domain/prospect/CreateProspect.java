package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
public interface CreateProspect {
    void createProspect(CreateProspectRequest request, Consumer<ProspectResponseModel> response)
            throws InvalidPhoneException, InvalidEmailException;
}
