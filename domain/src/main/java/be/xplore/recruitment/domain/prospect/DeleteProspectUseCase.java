package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
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
    public void deleteProspect(DeleteProspectRequest request, Consumer<List<ProspectResponseModel>> response)
            throws NotFoundException {
        Prospect prospect = repository.deleteProspect(request.prospectId).orElseThrow(NotFoundException::new);
        List<ProspectResponseModel> responseModel = new ArrayList<>(1);
        responseModel.add(new ProspectResponseModel(prospect));
        response.accept(responseModel);
    }
}
