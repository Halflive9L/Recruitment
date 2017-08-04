package be.xplore.recruitment.domain.interview;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class InterviewResponseModel {
    private long interviewId;
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private long applicantId;
    private List<Long> interviewerIds;

    public InterviewResponseModel() {
    }

    public long getInterviewId() {
        return interviewId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public List<Long> getInterviewerIds() {
        return interviewerIds;
    }

    public void setInterviewId(long interviewId) {
        this.interviewId = interviewId;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public void setInterviewerIds(List<Long> interviewerIds) {
        this.interviewerIds = interviewerIds;
    }

    public static InterviewResponseModel fromInterview(Interview interview) {
        InterviewResponseModel model = new InterviewResponseModel();
        model.setApplicantId(interview.getApplicant().getApplicantId());
        model.setScheduledTime(interview.getScheduledTime());
        model.setCreatedTime(interview.getCreatedTime());
        model.setInterviewId(interview.getInterviewId());
        model.setInterviewerIds(interview.getInterviewers().stream()
                .map(i -> i.getInterviewerId())
                .collect(Collectors.toList()));
        return model;
    }
}