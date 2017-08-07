package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.attachment.Attachment;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository {
    Interview createInterview(Interview interview);

    List<Interview> findAll();

    Optional<Interview> findById(long id);

    Optional<Interview> deleteInterviewer(long id);

    Optional<Interview> updateInterviewer(Interview interview);

    Optional<Interview> updateInterviewLocation(long interviewId, String location);

    Optional<Attachment> addAttachment(long interviewId, Attachment attachment);
}
