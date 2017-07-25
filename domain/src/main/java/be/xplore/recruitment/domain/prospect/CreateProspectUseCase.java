package be.xplore.recruitment.domain.prospect;

/**
 * @author Stijn Schack
 * @since 7/25/2017
 */
class CreateProspectUseCase {
    private final ProspectRepository repository;

    public CreateProspectUseCase(ProspectRepository repository) {
        this.repository = repository;
    }

    void createProspect(){
        Prospect prospect = new Prospect();
        repository.createProspect(prospect);
    }
}
