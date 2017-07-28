package be.xplore.recruitment.persistence.applicant;


import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static be.xplore.recruitment.persistence.applicant.JpaApplicant.QUERY_FIND_ALL;

/**
 * @author Lander
 * @since 26/07/2017
 */
@Component
@Transactional
public class ApplicantRepoJpa implements ApplicantRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void createApplicant(Applicant applicant) {
        JpaApplicant jpaApplicant = applicantToJpaApplicant(applicant);
        System.out.println(applicant);
        System.out.println(jpaApplicant);
        entityManager.persist(jpaApplicant);
    }

    private JpaApplicant applicantToJpaApplicant(Applicant applicant) {
        JpaApplicant jpaApplicant = new JpaApplicant();
        jpaApplicant.setFirstName(applicant.getFirstName());
        jpaApplicant.setLastName(applicant.getLastName());
        jpaApplicant.setEmail(applicant.getEmail());
        jpaApplicant.setPhone(applicant.getPhone());
        jpaApplicant.setDateOfBirth(applicant.getDateOfBirth());
        jpaApplicant.setAddress(applicant.getAddress());
        jpaApplicant.setEducation(applicant.getEducation());
        return jpaApplicant;
    }

    @Override
    public List<Applicant> findAll() {
        List<JpaApplicant> list = entityManager.createNamedQuery(QUERY_FIND_ALL)
                .getResultList();
        List<Applicant> result = list.stream().map(JpaApplicant::toApplicant).collect(Collectors.toList());
        return result;
    }

    @Override
    public Applicant findApplicantById(long id) {
        List<JpaApplicant> list = entityManager
                .createNamedQuery(JpaApplicant.QUERY_FIND_BY_ID)
                .setParameter("id", id).getResultList();
        if (list.isEmpty()) {
            return null;
        }

        JpaApplicant jpaApplicant = list.get(0);

        return jpaApplicant.toApplicant();
    }

    @Override
    public void updateApplicant(Applicant applicant) throws NotFoundException {

    }

    @Override
    public void deleteApplicant(long id) throws NotFoundException {

    }


}
