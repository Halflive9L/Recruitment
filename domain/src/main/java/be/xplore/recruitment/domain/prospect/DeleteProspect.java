package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.function.Consumer;

/**
 * @author Lander
 * @since 27/07/2017
 */
public interface DeleteProspect {
    void deleteProspect(DeleteProspectRequest request, Consumer<ProspectResponseModel> response)
            throws InvalidEmailException, InvalidPhoneException, NotFoundException;
}
