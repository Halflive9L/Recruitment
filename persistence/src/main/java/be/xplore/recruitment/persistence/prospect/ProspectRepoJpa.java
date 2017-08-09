package be.xplore.recruitment.persistence.prospect;


import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.domain.prospect.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static be.xplore.recruitment.persistence.prospect.JpaProspect.QUERY_FIND_ALL;

/**
 * @author Lander
 * @since 26/07/2017
 */
@Repository
@Transactional
public class ProspectRepoJpa implements ProspectRepository {

    private final EntityManager entityManager;

    @Autowired
    public ProspectRepoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Prospect createProspect(Prospect prospect) {
        JpaProspect jpaProspect = prospectToJpaProspect(prospect);
        entityManager.persist(jpaProspect);
        return jpaProspect.toProspect();
    }

    @Override
    public List<Prospect> findAll() {
        List<JpaProspect> list = entityManager.createNamedQuery(QUERY_FIND_ALL, JpaProspect.class)
                .getResultList();
        List<Prospect> result = list.stream().map(JpaProspect::toProspect).collect(Collectors.toList());
        return result;
    }

    @Override
    public Optional<Prospect> findProspectById(long prospectId) {
        List<JpaProspect> list = entityManager
                .createNamedQuery(JpaProspect.QUERY_FIND_BY_ID, JpaProspect.class)
                .setParameter("prospectId", prospectId).getResultList();
        JpaProspect jpaProspect = list.get(0);
        return Optional.ofNullable(jpaProspect.toProspect());
    }

    @Override
    public List<Prospect> findByParameters(Prospect prospect) throws NotFoundException {
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
    public Optional<Prospect> updateProspect(Prospect prospect) {
        JpaProspect jpaProspect = prospectToJpaProspect(prospect);
        jpaProspect.setProspectId(prospect.getProspectId());
        try {
            prospect = entityManager.merge(jpaProspect).toProspect();
        } catch (IllegalArgumentException e) {
            prospect = null;
        }
        return Optional.ofNullable(prospect);
    }

    @Override
    public Optional<Prospect> deleteProspect(long prospectId) {
        List<JpaProspect> prospectList = entityManager.createNamedQuery(JpaProspect.QUERY_FIND_BY_ID, JpaProspect.class)
                .setParameter("prospectId", prospectId).getResultList();
        entityManager.createNamedQuery(JpaProspect.QUERY_DELETE).setParameter("prospectId", prospectId)
                .executeUpdate();
        return Optional.ofNullable(prospectList.get(0).toProspect());
    }

    private JpaProspect prospectToJpaProspect(Prospect prospect) {
        JpaProspect jpaProspect = new JpaProspect();
        jpaProspect.setFirstName(prospect.getFirstName());
        jpaProspect.setLastName(prospect.getLastName());
        jpaProspect.setEmail(prospect.getEmail());
        jpaProspect.setPhone(prospect.getPhone());
        return jpaProspect;
    }
}
