package be.xplore.recruitment.domain.interview;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleInterviewRequest {
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private long applicantId;
    private List<Long> interviewerIds;
    private String location;
    private boolean preInterviewReminderSent;

    public ScheduleInterviewRequest() {
    }

    public static ScheduleInterviewRequestBuilder builder() {
        return ScheduleInterviewRequestBuilder.aScheduleInterviewRequest();
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

    public String getLocation() {
        return location;
    }

    public boolean isPreInterviewReminderSent() {
        return preInterviewReminderSent;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
    }
}
