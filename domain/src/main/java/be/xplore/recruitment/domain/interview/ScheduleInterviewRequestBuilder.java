package be.xplore.recruitment.domain.interview;

import java.time.LocalDateTime;
import java.util.List;

public final class ScheduleInterviewRequestBuilder {
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private long applicantId;
    private List<Long> interviewerIds;
    private String location;
    private boolean preInterviewReminderSent;

    private ScheduleInterviewRequestBuilder() {
    }

    public static ScheduleInterviewRequestBuilder aScheduleInterviewRequest() {
        return new ScheduleInterviewRequestBuilder();
    }

    public ScheduleInterviewRequestBuilder withCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public ScheduleInterviewRequestBuilder withScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
        return this;
    }

    public ScheduleInterviewRequestBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public ScheduleInterviewRequestBuilder withInterviewerIds(List<Long> interviewerIds) {
        this.interviewerIds = interviewerIds;
        return this;
    }

    public ScheduleInterviewRequestBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public ScheduleInterviewRequestBuilder withPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
        return this;
    }

    public ScheduleInterviewRequest build() {
        ScheduleInterviewRequest scheduleInterviewRequest = new ScheduleInterviewRequest();
        scheduleInterviewRequest.setCreatedTime(createdTime);
        scheduleInterviewRequest.setScheduledTime(scheduledTime);
        scheduleInterviewRequest.setApplicantId(applicantId);
        scheduleInterviewRequest.setInterviewerIds(interviewerIds);
        scheduleInterviewRequest.setLocation(location);
        scheduleInterviewRequest.setPreInterviewReminderSent(preInterviewReminderSent);
        return scheduleInterviewRequest;
    }
}
