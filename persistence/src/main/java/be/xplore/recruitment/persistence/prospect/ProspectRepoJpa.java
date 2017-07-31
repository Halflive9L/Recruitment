package be.xplore.recruitment.persistence.prospect;


import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static be.xplore.recruitment.persistence.prospect.JpaProspect.QUERY_FIND_ALL;

/**
 * @author Lander
 * @since 26/07/2017
 */
@Component
@Transactional
public class ProspectRepoJpa implements ProspectRepository {

    @Autowired
    private EntityManager entityManager;

    // public void setEntityManager(EntityManager entityManager) {
    //    this.entityManager = entityManager;
    // }

    @Override
    public void createProspect(Prospect prospect) {
        JpaProspect jpaProspectDatabaseInput = new JpaProspect();
        jpaProspectDatabaseInput.setFirstName(prospect.getFirstName());
        jpaProspectDatabaseInput.setLastName(prospect.getLastName());
        jpaProspectDatabaseInput.setEmail(prospect.getEmail());
        jpaProspectDatabaseInput.setPhone(prospect.getPhone());
        System.out.println(prospect);
        /*try {
            copyProperties(jpaProspectDatabaseInput, prospect);
        } catch (ReflectiveOperationException e) {
            System.out.println("Kopieren mislukt!");
        }*/
        entityManager.persist(jpaProspectDatabaseInput);
    }

    @Override
    public List<Prospect> findAll() {
        List<JpaProspect> list = entityManager.createNamedQuery(QUERY_FIND_ALL)
                .getResultList();
        List<Prospect> result = list.stream().map(this::toProspect).collect(Collectors.toList());
        return result;
    }

    @Override
    public Prospect findProspectById(long prospectId) {
        List<JpaProspect> list = entityManager
                .createNamedQuery(JpaProspect.QUERY_FIND_BY_ID)
                .setParameter("prospectId", prospectId).getResultList();
        if (list.isEmpty()) {
            return null;
        }

        JpaProspect jpaProspect = list.get(0);

        return toProspect(jpaProspect);
    }


    @Override
    public void deleteProspect(long prospectId) {
        entityManager.createNamedQuery(JpaProspect.QUERY_DELETE)
        .setParameter("prospectId", prospectId);
        //entityManager.remove(findProspectById(id));
    }

    @Override
    public Prospect updateProspect(Prospect prospect) {
        JpaProspect zoekProspect = (JpaProspect) entityManager.createNamedQuery(JpaProspect.QUERY_FIND_BY_ID)
                .setParameter("prospectId", prospect.getProspectId()).getSingleResult();
        zoekProspect.setEmail(prospect.getEmail());
        zoekProspect.setPhone(prospect.getPhone());
        zoekProspect.setLastName(prospect.getLastName());
        zoekProspect.setFirstName(prospect.getFirstName());
        entityManager.persist(zoekProspect);
        return toProspect(zoekProspect);
    }

    private Prospect toProspect(JpaProspect jpaProspect) {
        return new Prospect.ProspectBuilder(jpaProspect.getFirstName(), jpaProspect.getLastName())
                .withEmail(jpaProspect.getEmail())
                .withPhone(jpaProspect.getPhone()).withId(jpaProspect.getProspectId()).build();
    }


}
