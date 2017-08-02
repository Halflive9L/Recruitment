package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.function.Consumer;

/**
 * @author Lander
 * @since 27/07/2017
 */
public interface UpdateProspect {
    void updateProspect(UpdateProspectRequest request, Consumer<ProspectResponseModel> response)
            throws NotFoundException, InvalidEmailException, InvalidDateException;
}
