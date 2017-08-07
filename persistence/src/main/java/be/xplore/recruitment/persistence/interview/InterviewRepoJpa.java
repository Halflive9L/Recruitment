package be.xplore.recruitment.persistence.interview;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.interview.Interview;
import be.xplore.recruitment.domain.interview.InterviewRepository;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import be.xplore.recruitment.persistence.applicant.JpaApplicant;
import be.xplore.recruitment.persistence.interviewer.JpaInterviewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class InterviewRepoJpa implements InterviewRepository {
    private ApplicantRepository applicantRepository;
    private EntityManager entityManager;

    @Autowired
    public InterviewRepoJpa(EntityManager entityManager, ApplicantRepository applicantRepository) {
        this.entityManager = entityManager;
        this.applicantRepository = applicantRepository;
    }

    private static final String FIND_INTERVIEWERS = "SELECT i FROM JpaInterviewer i WHERE i.interviewerId IN (?1)";

    @Override
    public Interview createInterview(Interview interview) {
        JpaApplicant jpaApplicant = entityManager.find(JpaApplicant.class, interview.getApplicant().getApplicantId());
        Query query = entityManager.createQuery(FIND_INTERVIEWERS);
        query.setParameter(1, interview.getInterviewers().stream()
                .map(Interviewer::getInterviewerId)
                .collect(Collectors.toList()));
        List<JpaInterviewer> interviewers = (List<JpaInterviewer>) query.getResultList();
        JpaInterview jpaInterview = new JpaInterview();
        jpaInterview.setCreatedTime(interview.getCreatedTime());
        jpaInterview.setScheduledTime(interview.getScheduledTime());
        jpaInterview.setApplicant(jpaApplicant);
        jpaInterview.setInterviewers(interviewers);
        jpaInterview.setPreInterviewReminderSent(interview.isPreInterviewReminderSent());
        entityManager.persist(jpaInterview);
        return jpaInterview.toInterview();
    }

    private static final String FIND_ALL_QUERY = "SELECT i FROM JpaInterview i";
    @Override
    public List<Interview> findAll() {
        List<JpaInterview> interviews = entityManager.createQuery(FIND_ALL_QUERY, JpaInterview.class).getResultList();
        return interviews.stream()
                .map(i -> i.toInterview())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Interview> findById(long id) {
        JpaInterview jpaInterview = entityManager.find(JpaInterview.class, id);
        if (jpaInterview == null) {
            return Optional.empty();
        } else {
            return Optional.of(jpaInterview.toInterview());
        }
    }

    @Override
    public Optional<Interview> deleteInterviewer(long id) {
        return null;
    }

    @Override
    public Optional<Interview> updateInterviewer(Interview interview) {
        JpaInterview jpaInterview = entityManager.find(JpaInterview.class, interview.getInterviewId());
        if (jpaInterview == null) {
            return Optional.empty();
        }
        jpaInterview.setCreatedTime(interview.getCreatedTime());
        jpaInterview.setInterviewers(interview.getInterviewers().stream()
            .map(i -> entityManager.find(JpaInterviewer.class, i.getInterviewerId()))
            .collect(Collectors.toList()));
        jpaInterview.setApplicant(entityManager.find(JpaApplicant.class, interview.getApplicant().getApplicantId()));
        jpaInterview.setScheduledTime(interview.getScheduledTime());
        jpaInterview.setCancelled(interview.isCancelled());
        jpaInterview.setPreInterviewReminderSent(interview.isPreInterviewReminderSent());
        entityManager.persist(jpaInterview);
        return Optional.of(jpaInterview.toInterview());
    }

    private static final String FIND_REMIND_INTERVIEWS_QUERY =
            "SELECT JpaInterview i WHERE i.preInterviewReminderSent = false AND i.scheduledTime < :reminderCutoff";

    @Override
    public List<Interview> findInterviewsToRemind() {
        TypedQuery<JpaInterview> query = entityManager.createQuery(FIND_REMIND_INTERVIEWS_QUERY, JpaInterview.class);
        LocalDateTime cutoff = LocalDateTime.now().minusDays(1);
        query.setParameter("reminderCutoff", cutoff);
        return query.getResultList().stream()
            .map(JpaInterview::toInterview)
            .collect(Collectors.toList());
    }
}
