package be.xplore.recruitment.persistence.prospect;


import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        System.out.println("RepoProspect = " + prospect);
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
    public List<Prospect> findProspectByParam(Prospect prospect) throws NotFoundException {
        JpaProspect jpaProspect = prospectToJpaProspect(prospect);
        CriteriaQuery<JpaProspect> query = getJpaProspectCriteriaQuery(jpaProspect);
        List<JpaProspect> results = entityManager.createQuery(query).getResultList();
        return jpaProspectListToProspectList(results);
    }


    private List<Prospect> jpaProspectListToProspectList(List<JpaProspect> jpaProspects) {
        List<Prospect> prospects = new ArrayList<>(jpaProspects.size());
        jpaProspects.forEach(p -> prospects.add(p.toProspect()));
        return prospects;
    }

    private CriteriaQuery<JpaProspect> getJpaProspectCriteriaQuery(JpaProspect jpaProspect) {
        CriteriaQuery<JpaProspect> query = getCriteriaBuilder().createQuery(JpaProspect.class);
        Specification<JpaProspect> spec = new ProspectSpecification(jpaProspect).getFullSpecification();
        Root<JpaProspect> root = applySpecification(spec, query);
        System.out.println("root" + root);
        query.select(root);
        return query;
    }

    private Root<JpaProspect> applySpecification(Specification<JpaProspect> spec, CriteriaQuery<JpaProspect> query) {
        Root<JpaProspect> root = query.from(JpaProspect.class);
        Predicate predicate = spec.toPredicate(root, query, getCriteriaBuilder());
        if (predicate != null) {
            query.where(predicate);
        }
        return root;
    }

    private CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }



    @Override
    public void deleteProspect(long prospectId) {
        entityManager.createNamedQuery(JpaProspect.QUERY_DELETE)
        .setParameter("prospectId", prospectId).executeUpdate();
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

    private JpaProspect prospectToJpaProspect(Prospect prospect) {
        JpaProspect jpaProspect= new JpaProspect();
        jpaProspect.setFirstName(prospect.getFirstName());
        jpaProspect.setLastName(prospect.getLastName());
        jpaProspect.setEmail(prospect.getEmail());
        jpaProspect.setPhone(prospect.getPhone());
        return jpaProspect;
    }


}
