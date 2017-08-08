package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interviewer.Interviewer;

import java.time.LocalDateTime;
import java.util.List;

public class Interview {
    private long interviewId;
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private Applicant applicant;
    private List<Interviewer> interviewers;
    private boolean cancelled;
    private String location;
    private boolean preInterviewReminderSent;

    public Interview() {
    }

    public static InterviewBuilder builder() {
        return new InterviewBuilder();
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

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<Interviewer> getInterviewers() {
        return interviewers;
    }

    public void setInterviewers(List<Interviewer> interviewers) {
        this.interviewers = interviewers;
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

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", createdTime=" + createdTime +
                ", scheduledTime=" + scheduledTime +
                ", applicant=" + applicant +
                ", interviewers=" + interviewers +
                ", cancelled=" + cancelled +
                ", location='" + location + '\'' +
                '}';
    }

    public static final class InterviewBuilder {
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

        public InterviewBuilder withPreInterviewReminderSent(boolean sent) {
            this.preInterviewReminderSent = sent;
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
}
