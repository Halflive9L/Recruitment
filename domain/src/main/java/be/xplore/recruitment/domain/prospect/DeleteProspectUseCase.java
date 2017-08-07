package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

/**
 * @author Lander
 * @since 27/07/2017
 */
@Named
public class DeleteProspectUseCase implements DeleteProspect {

    private ProspectRepository repository;

    public DeleteProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteProspect(DeleteProspectRequest request, Consumer<ProspectResponseModel> response)
            throws NotFoundException {
        Prospect prospect = repository.deleteProspect(request.prospectId).orElseThrow(NotFoundException::new);
        response.accept(new ProspectResponseModel(prospect));
    }
}
