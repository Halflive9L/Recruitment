package be.xplore.recruitment.persistence.interviewer;

import be.xplore.recruitment.domain.interviewer.Interviewer;
import be.xplore.recruitment.domain.interviewer.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class InterviewerRepoJpa implements InterviewerRepository {
    private static final String FIND_ALL_QUERY = "SELECT i FROM JpaInterviewer i";
    private final EntityManager entityManager;

    @Autowired
    public InterviewerRepoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Interviewer createInterviewer(Interviewer interviewer) {
        JpaInterviewer entity = JpaInterviewer.fromInterviewer(interviewer);
        entityManager.persist(entity);
        return entity.toInterviewer();
    }

    @Override
    public List<Interviewer> findAll() {
        List<JpaInterviewer> result = entityManager.createQuery(FIND_ALL_QUERY, JpaInterviewer.class).getResultList();
        return result.stream()
                .map(JpaInterviewer::toInterviewer)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Interviewer> findById(long id) {
        JpaInterviewer e = entityManager.find(JpaInterviewer.class, id);
        if (e == null) {
            return Optional.empty();
        } else {
            return Optional.of(e.toInterviewer());
        }
    }

    @Override
    public Optional<Interviewer> deleteInterviewer(long id) {
        JpaInterviewer e = entityManager.find(JpaInterviewer.class, id);
        return Optional.ofNullable(entityManager.find(JpaInterviewer.class, id))
                .map(this::removeInterviewer);
    }

    @Override
    public Optional<Interviewer> updateInterviewer(Interviewer interviewer) {
        return Optional.ofNullable(entityManager.find(JpaInterviewer.class, interviewer.getInterviewerId()))
                .map(e -> updateInterview(interviewer, e));
    }

    private Interviewer updateInterview(Interviewer interviewer, JpaInterviewer e) {
        updateInterviewerFields(interviewer, e);
        entityManager.persist(e);
        return e.toInterviewer();
    }

    private void updateInterviewerFields(Interviewer interviewer, JpaInterviewer e) {
        e.setFirstName(interviewer.getFirstName());
        e.setLastName(interviewer.getLastName());
        e.setEmail(interviewer.getEmail());
    }

    private Interviewer removeInterviewer(JpaInterviewer interviewer) {
        entityManager.remove(interviewer);
        return interviewer.toInterviewer();
    }
}
