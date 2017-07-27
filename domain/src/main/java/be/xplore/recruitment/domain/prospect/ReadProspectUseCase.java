package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public class ReadProspectUseCase implements ReadProspect {

    private final ProspectRepository repository;

    public ReadProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void readAllProspects(ReadAllProspectsResponse response) {
        response.onResponse(repository.findAll());
    }

    @Override
    public void readProspectById(ReadProspectRequest request, ReadProspectResponse response) {
        if (repository.findProspectById(request.prospectId) == null) throw new NotFoundException();
        response.onResponse(repository.findProspectById(request.prospectId));

    }
}
