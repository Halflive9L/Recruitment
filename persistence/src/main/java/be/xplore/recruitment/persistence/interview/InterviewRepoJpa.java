package be.xplore.recruitment.persistence.interview;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
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
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class InterviewRepoJpa implements InterviewRepository {
    private static final String FIND_INTERVIEWERS = "SELECT i FROM JpaInterviewer i WHERE i.interviewerId IN (?1)";
    private static final String FIND_ALL_QUERY = "SELECT i FROM JpaInterview i";
    private ApplicantRepository applicantRepository;
    private EntityManager entityManager;
    private FileManager fileManager;

    @Autowired
    public InterviewRepoJpa(EntityManager entityManager, ApplicantRepository applicantRepository,
                            FileManager fileManager) {
        this.entityManager = entityManager;
        this.applicantRepository = applicantRepository;
        this.fileManager = fileManager;
    }

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
        entityManager.persist(jpaInterview);
        return jpaInterview.toInterview();
    }

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
        entityManager.persist(jpaInterview);
        return Optional.of(jpaInterview.toInterview());
    }

    @Override
    public Optional<Attachment> addAttachment(long interviewId, Attachment attachment)
            throws NotFoundException {
        JpaInterview interview = entityManager.find(JpaInterview.class, interviewId);
        if (interview == null) {
            throw new NotFoundException("Interview with ID: " + interviewId + " does not exist.");
        }

        Optional<Attachment> createdAttachment = Optional.ofNullable(tryCreateAttachment(attachment));
        createdAttachment.ifPresent(a -> registerAttachment(interview, a.getAttachmentName()));
        return createdAttachment;
    }

    private Attachment tryCreateAttachment(Attachment attachment) {
        try {
            attachment.setAttachmentName(fileManager.createFile(attachment.getInputStream(),
                    "interview", attachment.getAttachmentName()));
            return attachment;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void registerAttachment(JpaInterview interview, String fileName) {
        JpaAttachment attachment = new JpaAttachment(interview, fileName);
        entityManager.persist(attachment);
    }
}
