package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interviewer.Interviewer;

import java.time.LocalDateTime;
import java.util.List;

public final class InterviewBuilder {
    private long interviewId;
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private Applicant applicant;
    private List<Interviewer> interviewers;
    private boolean cancelled;
    private String location;
    private boolean preInterviewReminderSent;

    private InterviewBuilder() {
    }

    public static InterviewBuilder anInterview() {
        return new InterviewBuilder();
    }

    public InterviewBuilder withInterviewId(long interviewId) {
        this.interviewId = interviewId;
        return this;
    }

    public InterviewBuilder withCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public InterviewBuilder withScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
        return this;
    }

    public InterviewBuilder withApplicant(Applicant applicant) {
        this.applicant = applicant;
        return this;
    }

    public InterviewBuilder withInterviewers(List<Interviewer> interviewers) {
        this.interviewers = interviewers;
        return this;
    }

    public InterviewBuilder withCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        return this;
    }

    public InterviewBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public InterviewBuilder withPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
        return this;
    }

    public Interview build() {
        Interview interview = new Interview();
        interview.setInterviewId(interviewId);
        interview.setCreatedTime(createdTime);
        interview.setScheduledTime(scheduledTime);
        interview.setApplicant(applicant);
        interview.setInterviewers(interviewers);
        interview.setCancelled(cancelled);
        interview.setLocation(location);
        interview.setPreInterviewReminderSent(preInterviewReminderSent);
        return interview;
    }
}
