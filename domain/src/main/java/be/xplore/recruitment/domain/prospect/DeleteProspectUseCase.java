package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

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
    public void deleteProspect(DeleteProspectRequest request, DeleteProspectResponse response) {
        if (repository.findProspectById(request.prospectId) == null) {
            throw new NotFoundException();
        }
        repository.deleteProspect(request.prospectId);
        response.onResponse(request.prospectId);
    }
}
