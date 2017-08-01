package be.xplore.recruitment.persistence.prospect;

import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/31/2017
 */
@Component
@Transactional
public class ProspectRepoJpa implements ProspectRepository {
    @Override
    public void createProspect(Prospect prospect) {

    }

    @Override
    public List<Prospect> findAll() {
        return null;
    }

    @Override
    public Prospect findProspectById(long id) {
        return null;
    }
}
