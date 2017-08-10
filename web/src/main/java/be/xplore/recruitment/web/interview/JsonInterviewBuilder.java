package be.xplore.recruitment.web.interview;

import java.time.LocalDateTime;
import java.util.List;

public final class JsonInterviewBuilder {
    private long interviewId;
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private long applicantId;
    private List<Long> interviewerIds;
    private boolean cancelled;
    private String location;
    private boolean preInterviewReminderSent;

    private JsonInterviewBuilder() {
    }

    public static JsonInterviewBuilder aJsonInterview() {
        return new JsonInterviewBuilder();
    }

    public JsonInterviewBuilder withInterviewId(long interviewId) {
        this.interviewId = interviewId;
        return this;
    }

    public JsonInterviewBuilder withCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public JsonInterviewBuilder withScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
        return this;
    }

    public JsonInterviewBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public JsonInterviewBuilder withInterviewerIds(List<Long> interviewerIds) {
        this.interviewerIds = interviewerIds;
        return this;
    }

    public JsonInterviewBuilder withCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        return this;
    }

    public JsonInterviewBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public JsonInterviewBuilder withPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
        return this;
    }

    public JsonInterview build() {
        JsonInterview jsonInterview = new JsonInterview();
        jsonInterview.setInterviewId(interviewId);
        jsonInterview.setCreatedTime(createdTime);
        jsonInterview.setScheduledTime(scheduledTime);
        jsonInterview.setApplicantId(applicantId);
        jsonInterview.setInterviewerIds(interviewerIds);
        jsonInterview.setCancelled(cancelled);
        jsonInterview.setLocation(location);
        jsonInterview.setPreInterviewReminderSent(preInterviewReminderSent);
        return jsonInterview;
    }
}
