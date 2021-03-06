package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@Named
public class ReadProspectUseCase implements ReadProspect {
    private final ProspectRepository repository;

    ReadProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }


    @Override
    public void readAllProspects(Consumer<List<ProspectResponseModel>> response) {
        List<Prospect> prospects = repository.findAll();
        List<ProspectResponseModel> responseList = getResponseListFromProspectList(prospects);
        response.accept(responseList);
    }

    @Override
    public void readProspectsByParam(ReadProspectRequest request, Consumer<List<ProspectResponseModel>> response)
            throws NotFoundException {
        List<Prospect> prospects = repository.findByParameters(request.toProspect());
        if (prospects.isEmpty()) {
            throw new NotFoundException();
        }
        response.accept(getResponseListFromProspectList(prospects));
    }

    private List<ProspectResponseModel> getResponseListFromProspectList(List<Prospect> prospects) {
        List<ProspectResponseModel> responseList = new LinkedList<>();
        prospects.forEach(prospect -> responseList.add(new ProspectResponseModel(prospect)));
        return responseList;
    }

    @Override
    public void readProspectById(ReadProspectRequest request, Consumer<ProspectResponseModel> consumer)
            throws NotFoundException {
        Prospect prospect = repository.findProspectById(request.prospectId).orElseThrow(NotFoundException::new);
        consumer.accept(new ProspectResponseModel(prospect));
    }
}
