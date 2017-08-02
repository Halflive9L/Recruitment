package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadProspect {

    void readAllProspects(Consumer<List<ProspectResponseModel>> response);

    void readProspectById(ReadProspectRequest request, Consumer<List<ProspectResponseModel>> response)
            throws NotFoundException;

    void readProspectsByParam(ReadProspectRequest request, Consumer<List<ProspectResponseModel>> response)
            throws NotFoundException;
}
