package be.xplore.recruitment.persistence.interview;

import be.xplore.recruitment.persistence.applicant.JpaApplicant;
import be.xplore.recruitment.persistence.interviewer.JpaInterviewer;
import be.xplore.recruitment.persistence.interviewer.JpaInterviewerBuilder;

import java.time.LocalDateTime;
import java.util.List;

public final class JpaInterviewBuilder {
    private long interviewId;
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private JpaApplicant applicant;
    private List<JpaInterviewer> interviewers;
    private boolean cancelled;
    private String location;
    private boolean feedbackReminderSent;
    private boolean preInterviewReminderSent;
    private JpaInterview in;

    private JpaInterviewBuilder() {
    }

    private JpaInterviewBuilder(JpaInterview interview) {
        this.in = interview;
        this.interviewId = interview.getInterviewId();
        this.createdTime = interview.getCreatedTime();
        this.scheduledTime = interview.getScheduledTime();
        this.applicant = interview.getApplicant();
        this.interviewers = interview.getInterviewers();
        this.cancelled = interview.isCancelled();
        this.location = interview.getLocation();
        this.feedbackReminderSent = interview.isFeedbackReminderSent();
        this.preInterviewReminderSent = interview.isPreInterviewReminderSent();
    }

    public static JpaInterviewBuilder aJpaInterview() {
        return new JpaInterviewBuilder();
    }

    public static JpaInterviewBuilder aJpaInterview(JpaInterview interview) {
        return new JpaInterviewBuilder(interview);
    }

    public JpaInterviewBuilder withInterviewId(long interviewId) {
        this.interviewId = interviewId;
        return this;
    }

    public JpaInterviewBuilder withCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public JpaInterviewBuilder withFeedbackReminderSent(boolean sent) {
        this.feedbackReminderSent = sent;
        return this;
    }

    public JpaInterviewBuilder withScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
        return this;
    }

    public JpaInterviewBuilder withApplicant(JpaApplicant applicant) {
        this.applicant = applicant;
        return this;
    }

    public JpaInterviewBuilder withInterviewers(List<JpaInterviewer> interviewers) {
        this.interviewers = interviewers;
        return this;
    }

    public JpaInterviewBuilder withCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        return this;
    }

    public JpaInterviewBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public JpaInterviewBuilder withPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
        return this;
    }

    public JpaInterview build() {
        JpaInterview jpaInterview;
        if (in != null) {
            jpaInterview = in;
        }
        else {
            jpaInterview = new JpaInterview();
        }
        jpaInterview.setInterviewId(interviewId);
        jpaInterview.setCreatedTime(createdTime);
        jpaInterview.setScheduledTime(scheduledTime);
        jpaInterview.setApplicant(applicant);
        jpaInterview.setInterviewers(interviewers);
        jpaInterview.setCancelled(cancelled);
        jpaInterview.setLocation(location);
        jpaInterview.setFeedbackReminderSent(feedbackReminderSent);
        jpaInterview.setPreInterviewReminderSent(preInterviewReminderSent);
        return jpaInterview;
    }
}
