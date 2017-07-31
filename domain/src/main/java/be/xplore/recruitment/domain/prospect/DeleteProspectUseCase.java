package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.function.Consumer;

/**
 * @author Lander
 * @since 27/07/2017
 */
public class DeleteProspectUseCase implements DeleteProspect {

    private final ProspectRepository repository;

    public DeleteProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteProspect(DeleteProspectRequest request, Consumer<Long> response) {
        repository.deleteProspect(request.prospectId);
        response.accept(request.prospectId);
    }
}
