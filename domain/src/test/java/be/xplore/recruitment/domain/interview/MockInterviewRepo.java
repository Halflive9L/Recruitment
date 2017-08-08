package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.attachment.Attachment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockInterviewRepo implements InterviewRepository {
    private List<Interview> data;
    private long currentId = 0;
    private List<Attachment> attachments;

    public MockInterviewRepo() {
        this.data = new ArrayList<>();
        this.attachments = new ArrayList<>();
    }

    public MockInterviewRepo(List<Interview> data) {
        this.data = data;
        this.currentId = data.stream().mapToLong(Interview::getInterviewId).max().getAsLong();
        this.attachments = new ArrayList<>();
    }

    @Override
    public Interview createInterview(Interview interview) {
        interview.setInterviewId(++currentId);
        data.add(interview);
        return interview;
    }

    @Override
    public List<Interview> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Interview> findById(long id) {
        return data.stream().filter(i -> i.getInterviewId() == id).findFirst();
    }

    @Override
    public Optional<Interview> deleteInterviewer(long id) {
        return null;
    }

    @Override
    public Optional<Interview> updateInterviewer(Interview interview) {
        return data.stream()
                .filter(i -> i.getInterviewId() == interview.getInterviewId())
                .findFirst()
                .map(i -> {
                    i.setCancelled(interview.isCancelled());
                    i.setInterviewId(interview.getInterviewId());
                    i.setApplicant(interview.getApplicant());
                    i.setInterviewers(interview.getInterviewers());
                    i.setCreatedTime(interview.getCreatedTime());
                    i.setScheduledTime(interview.getScheduledTime());
                    return i;
                });
    }

    @Override
    public Optional<Interview> updateInterviewLocation(long interviewId, String location) {
        return null;
    }

    @Override
    public Optional<Attachment> addAttachment(long interviewId, Attachment attachment) {
        for (Interview i : data) {
            if (i.getInterviewId() == interviewId) {
                attachment.setAttachmentId(attachments.size() == 0 ? 1 : attachments.size());
                attachments.add(attachment);
                return Optional.of(attachment);
            }
        }
        return Optional.empty();
    }
    public List<Attachment> getAttachments() {
        return attachments;
    }
}
