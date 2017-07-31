package be.xplore.recruitment.domain.prospect;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadProspect {

    void readAllProspects(Consumer<List<ProspectResponseModel>> response);

    void readProspectById(ReadProspectRequest request, Consumer<ProspectResponseModel> response);

    void readProspectByParam(ReadProspectRequest request, Consumer<List<ProspectResponseModel>> response);
}
