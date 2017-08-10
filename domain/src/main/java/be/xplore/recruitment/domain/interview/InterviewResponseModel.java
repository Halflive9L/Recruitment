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
    private boolean cancelled;
    private String location;
    private boolean preInterviewReminderSent;

    public InterviewResponseModel() {
    }

    public static InterviewResponseModel fromInterview(Interview interview) {
        return InterviewResponseModelBuilder.anInterviewResponseModel()
                .withApplicantId(interview.getApplicant().getApplicantId())
                .withScheduledTime(interview.getScheduledTime())
                .withCreatedTime(interview.getCreatedTime())
                .withInterviewId(interview.getInterviewId())
                .withInterviewerIds(interview.getInterviewers().stream()
                        .map(i -> i.getInterviewerId())
                        .collect(Collectors.toList()))
                .withCancelled(interview.isCancelled())
                .withLocation(interview.getLocation())
                .withPreInterviewReminderSent(interview.isPreInterviewReminderSent())
                .build();
    }

    public long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(long interviewId) {
        this.interviewId = interviewId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public List<Long> getInterviewerIds() {
        return interviewerIds;
    }

    public void setInterviewerIds(List<Long> interviewerIds) {
        this.interviewerIds = interviewerIds;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isPreInterviewReminderSent() {
        return preInterviewReminderSent;
    }

    public void setPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
    }
}
