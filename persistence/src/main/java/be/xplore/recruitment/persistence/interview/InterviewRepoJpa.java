package be.xplore.recruitment.persistence.interview;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.interview.Interview;
import be.xplore.recruitment.domain.interview.InterviewRepository;
import be.xplore.recruitment.domain.interviewer.Interviewer;
import be.xplore.recruitment.persistence.applicant.JpaApplicant;
import be.xplore.recruitment.persistence.attachment.FileManager;
import be.xplore.recruitment.persistence.attachment.JpaAttachment;
import be.xplore.recruitment.persistence.interviewer.JpaInterviewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class InterviewRepoJpa implements InterviewRepository {
    private static final String FIND_INTERVIEWERS = "SELECT i FROM JpaInterviewer i WHERE i.interviewerId IN (?1)";
    private static final String FIND_ALL_QUERY = "SELECT i FROM JpaInterview i";
    private static final String FIND_REMIND_INTERVIEWS_QUERY = "SELECT i FROM JpaInterview i WHERE " +
            "i.preInterviewReminderSent = false AND i.cancelled = false AND :reminderCutoff > i.scheduledTime";
    private EntityManager entityManager;
    private FileManager fileManager;

    @Autowired
    public InterviewRepoJpa(EntityManager entityManager, FileManager fileMgr) {
        this.entityManager = entityManager;
        this.fileManager = fileMgr;
    }

    @Override
    public Interview createInterview(Interview interview) {
        JpaApplicant jpaApplicant = entityManager.find(JpaApplicant.class, interview.getApplicant().getApplicantId());
        JpaInterview jpaInterview = JpaInterviewBuilder.aJpaInterview()
                .withCreatedTime(interview.getCreatedTime())
                .withScheduledTime(interview.getScheduledTime())
                .withApplicant(jpaApplicant)
                .withInterviewers(getJpaInterviewers(interview))
                .withLocation(interview.getLocation())
                .withPreInterviewReminderSent(interview.isPreInterviewReminderSent())
                .build();
        entityManager.persist(jpaInterview);
        return jpaInterview.toInterview();
    }

    private List<JpaInterviewer> getJpaInterviewers(Interview interview) {
        TypedQuery<JpaInterviewer> query = entityManager.createQuery(FIND_INTERVIEWERS, JpaInterviewer.class);
        query.setParameter(1, interview.getInterviewers().stream()
                .map(Interviewer::getInterviewerId)
                .collect(Collectors.toList()));
        return query.getResultList();
    }

    @Override
    public List<Interview> findAll() {
        List<JpaInterview> interviews = entityManager.createQuery(FIND_ALL_QUERY, JpaInterview.class).getResultList();
        return interviews.stream().map(JpaInterview::toInterview).collect(Collectors.toList());
    }

    @Override
    public Optional<Interview> findById(long id) {
        JpaInterview jpaInterview = entityManager.find(JpaInterview.class, id);
        return jpaInterview == null ? Optional.empty() : Optional.of(jpaInterview.toInterview());
    }

    @Override
    public Optional<Interview> deleteInterviewer(long id) {
        return null;
    }

    @Override
    public Optional<Interview> updateInterviewer(Interview interview) {
        JpaInterview jpaInterview = entityManager.find(JpaInterview.class, interview.getInterviewId());
        return Optional.ofNullable(jpaInterview).map(i -> this.updateJpaInterview(interview, i).toInterview());
    }

    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    private JpaInterview updateJpaInterview(Interview interview, JpaInterview jpaInterview) {
        JpaInterview e =  JpaInterviewBuilder.aJpaInterview(jpaInterview)
                .withCreatedTime(interview.getCreatedTime())
                .withInterviewers(interview.getInterviewers().stream()
                        .map(i -> entityManager.find(JpaInterviewer.class, i.getInterviewerId()))
                        .collect(Collectors.toList()))
                .withApplicant(entityManager.find(JpaApplicant.class, interview.getApplicant().getApplicantId()))
                .withScheduledTime(interview.getScheduledTime())
                .withCancelled(interview.isCancelled())
                .withPreInterviewReminderSent(interview.isPreInterviewReminderSent())
                .build();
        entityManager.persist(e);
        return e;
    }

    @Override
    public Optional<Interview> updateInterviewLocation(long interviewId, String location) {
        JpaInterview jpaInterview = entityManager.find(JpaInterview.class, interviewId);
        return Optional.ofNullable(jpaInterview).map(i -> this.updateJpaInterviewLocation(location, i));
    }

    @Override
    public Optional<Attachment> addAttachment(long interviewId, Attachment attachment) throws NotFoundException {
        JpaInterview interview = entityManager.find(JpaInterview.class, interviewId);
        if (interview == null) {
            throw new NotFoundException("Interview with ID: " + interviewId + " does not exist.");
        }
        return Optional.ofNullable(tryCreateAttachment(attachment)).map(a -> registerAttachment(interview, a));
    }

    private Attachment registerAttachment(JpaInterview interview, Attachment a) {
        a.setAttachmentId(registerAttachment(interview, a.getAttachmentName()));
        return a;
    }

    @Override
    public List<Interview> findInterviewsToRemind() {
        TypedQuery<JpaInterview> query = entityManager.createQuery(FIND_REMIND_INTERVIEWS_QUERY, JpaInterview.class);
        query.setParameter("reminderCutoff", LocalDateTime.now().plusDays(1));
        return query.getResultList().stream().map(JpaInterview::toInterview).collect(Collectors.toList());
    }

    private Interview updateJpaInterviewLocation(String location, JpaInterview jpaInterview) {
        jpaInterview.setLocation(location);
        entityManager.merge(jpaInterview);
        return jpaInterview.toInterview();
    }

    private Attachment tryCreateAttachment(Attachment attachment) {
        try {
            attachment.setAttachmentName(fileManager.createFile(attachment.getInputStream(),
                    "interview", attachment.getAttachmentName()));
            return attachment;
        } catch (IOException e) {
            return null;
        }
    }

    private long registerAttachment(JpaInterview interview, String fileName) {
        JpaAttachment attachment = new JpaAttachment(interview, fileName);
        entityManager.persist(attachment);
        return attachment.getAttachmentId();
    }
}
