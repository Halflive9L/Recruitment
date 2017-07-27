package be.xplore.recruitment.domain.prospect;

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
    public List<Prospect> readAllProspects() {
        return repository.findAll();
    }

    @Override
    public Prospect readProspectById(long id) {
        return repository.findProspectById(id);
    }
}
