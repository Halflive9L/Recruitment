package be.xplore.recruitment.persistence.prospect;


import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import static be.xplore.recruitment.persistence.prospect.JpaProspect.QUERY_FIND_ALL;
import static org.apache.commons.beanutils.BeanUtils.copyProperties;

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
        /*jpaProspectDatabaseInput.setFirstName(prospect.getFirstName());
        jpaProspectDatabaseInput.setLastName(prospect.getLastName());
        jpaProspectDatabaseInput.setEmail(prospect.getEmail());
        jpaProspectDatabaseInput.setPhone(prospect.getPhone());*/
        System.out.println(prospect);
        try {
            copyProperties(jpaProspectDatabaseInput, prospect);
        } catch (ReflectiveOperationException e) {
            System.out.println("Kopieren mislukt!");
        }
        System.out.println(jpaProspectDatabaseInput);
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

    @Override
    public void deleteProspect(long id) {

    }

    @Override
    public Prospect updateProspect() {
        return null;
    }

    private Prospect toProspect(JpaProspect jpaProspect) {
        return new Prospect.ProspectBuilder(jpaProspect.getFirstName(), jpaProspect.getLastName())
                .withEmail(jpaProspect.getEmail())
                .withPhone(jpaProspect.getPhone()).build();
    }


}
