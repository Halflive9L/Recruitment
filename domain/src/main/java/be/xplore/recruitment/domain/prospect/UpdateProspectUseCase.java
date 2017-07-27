package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;

import static be.xplore.recruitment.domain.prospect.Prospect.builder;

/**
 * @author Lander
 * @since 27/07/2017
 */
public class UpdateProspectUseCase implements  UpdateProspect{

    private final ProspectRepository repository;

    public UpdateProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateProspect(UpdateProspectRequest request, UpdateProspectResponse response) {
        if(repository.findProspectById(request.prospectId) == null) throw new NotFoundException();
        Prospect prospect = repository.findProspectById(request.prospectId);
        System.out.println(prospect);
        Prospect prospect1 = builder(request.firstName, request.lastName)
                .withEmail(request.email)
                .withPhone(request.phone)
                .withId(prospect.getProspectId()).build();
        repository.createProspect(prospect1);
    }
}
