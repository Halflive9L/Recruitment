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
    public void readProspectById(ReadProspectRequest request, Consumer<List<ProspectResponseModel>> response) {
        if (repository.findProspectById(request.prospectId) == null) {
            throw new NotFoundException();
        }
        List<ProspectResponseModel> prospectResponseModels = new ArrayList<>();
        prospectResponseModels.add(new ProspectResponseModel(repository.findProspectById(request.prospectId)));
        response.accept(prospectResponseModels);
    }

    @Override
    public void readProspectByParam(ReadProspectRequest request, Consumer<List<ProspectResponseModel>> response) {
        if(isEmptyRequest(request)) {
            readAllProspects(response);
        } else {

        }
    }

    private boolean isEmptyRequest(ReadProspectRequest request) {
        return request.firstName == null && request.lastName == null && request.phone == null && request.email == null;
    }
}
