package be.xplore.recruitment.domain.prospect;

import java.util.function.Consumer;

/**
 * @author Lander
 * @since 27/07/2017
 */
public interface UpdateProspect {
    void updateProspect(UpdateProspectRequest request, Consumer<ProspectResponseModel> response);
}
