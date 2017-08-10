package be.xplore.recruitment.domain.interview;

import java.time.LocalDateTime;
import java.util.List;

public final class InterviewResponseModelBuilder {
    private long interviewId;
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private long applicantId;
    private List<Long> interviewerIds;
    private boolean cancelled;
    private String location;
    private boolean preInterviewReminderSent;

    private InterviewResponseModelBuilder() {
    }

    public static InterviewResponseModelBuilder anInterviewResponseModel() {
        return new InterviewResponseModelBuilder();
    }

    public InterviewResponseModelBuilder withInterviewId(long interviewId) {
        this.interviewId = interviewId;
        return this;
    }

    public InterviewResponseModelBuilder withCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public InterviewResponseModelBuilder withScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
        return this;
    }

    public InterviewResponseModelBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public InterviewResponseModelBuilder withInterviewerIds(List<Long> interviewerIds) {
        this.interviewerIds = interviewerIds;
        return this;
    }

    public InterviewResponseModelBuilder withCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        return this;
    }

    public InterviewResponseModelBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public InterviewResponseModelBuilder withPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
        return this;
    }

    public InterviewResponseModel build() {
        InterviewResponseModel interviewResponseModel = new InterviewResponseModel();
        interviewResponseModel.setInterviewId(interviewId);
        interviewResponseModel.setCreatedTime(createdTime);
        interviewResponseModel.setScheduledTime(scheduledTime);
        interviewResponseModel.setApplicantId(applicantId);
        interviewResponseModel.setInterviewerIds(interviewerIds);
        interviewResponseModel.setCancelled(cancelled);
        interviewResponseModel.setLocation(location);
        interviewResponseModel.setPreInterviewReminderSent(preInterviewReminderSent);
        return interviewResponseModel;
    }
}
