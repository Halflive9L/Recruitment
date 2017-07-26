package be.xplore.recruitment.persistence.prospect;


import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.domain.prospect.ProspectRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static be.xplore.recruitment.persistence.prospect.JpaProspect.*;

/**
 * Created by Lander on 26/07/2017.
 */
public class ProspectRepoJpa implements ProspectRepository {

    @Inject
    private EntityManager entityManager;


    @Override
    public void createProspect(Prospect prospect) {
        JpaProspect jpaProspectDatabaseInput = new JpaProspect();
        jpaProspectDatabaseInput.setFirstName(prospect.getFirstName());
        jpaProspectDatabaseInput.setLastName(prospect.getLastName());
        jpaProspectDatabaseInput.setEmail(prospect.getEmail());
        jpaProspectDatabaseInput.setPhone(prospect.getPhone());
        entityManager.persist(jpaProspectDatabaseInput);
    }

    @Override
    public List<Prospect> findAll() {
        List<JpaProspect> list = entityManager.createNamedQuery(QUERY_FIND_ALL)
                .getResultList();
        List<Prospect> result = new ArrayList<>();
        for (JpaProspect p : list) {
            result.add(toProspect(p));
        }
    return result;
}

    private Prospect toProspect(JpaProspect jpaProspect) {
        Prospect result = new Prospect();
        result.setFirstName(jpaProspect.getFirstName());
        result.setPhone(jpaProspect.getPhone());
        result.setLastName(jpaProspect.getLastName());
        result.setEmail(jpaProspect.getEmail());
        return result;
    }

    @Override
    public Prospect findProspectById(long id) {
        List<JpaProspect> list = entityManager
                .createNamedQuery(JpaProspect.QUERY_FIND_BY_ID)
                .setParameter("id", id).getResultList();
        if (list.isEmpty()) {
            return null;
        }

        JpaProspect jpaProspect = list.get(0);

        return toProspect(jpaProspect);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
