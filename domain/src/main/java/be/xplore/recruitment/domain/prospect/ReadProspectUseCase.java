package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@Named
public class ReadProspectUseCase implements ReadProspect {

    private final ProspectRepository repository;

    public ReadProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void readAllProspects(Consumer<List<ProspectResponseModel>> response) {
        List<Prospect> prospects = repository.findAll();
        List<ProspectResponseModel> prospectResponseModels = new ArrayList<>();
        for(Prospect p : prospects) {
            prospectResponseModels.add(new ProspectResponseModel(p));
        }
        response.accept(prospectResponseModels);
    }

    @Override
    public void readProspectById(ReadProspectRequest request, Consumer<ProspectResponseModel> response) {
        if (repository.findProspectById(request.prospectId) == null) {
            throw new NotFoundException();
        }
        response.accept(new ProspectResponseModel(repository.findProspectById(request.prospectId)));
    }
}
