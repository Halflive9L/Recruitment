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

    public Interview() {
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

    public Applicant getApplicant() {
        return applicant;
    }

    public List<Interviewer> getInterviewers() {
        return interviewers;
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

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public void setInterviewers(List<Interviewer> interviewers) {
        this.interviewers = interviewers;
    }

    public static InterviewBuilder builder() {
        return new InterviewBuilder();
    }

    public static final class InterviewBuilder {
        private long interviewId;
        private LocalDateTime createdTime;
        private LocalDateTime scheduledTime;
        private Applicant applicant;
        private List<Interviewer> interviewers;

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

        public Interview build() {
            Interview interview = new Interview();
            interview.interviewId = this.interviewId;
            interview.interviewers = this.interviewers;
            interview.scheduledTime = this.scheduledTime;
            interview.applicant = this.applicant;
            interview.createdTime = this.createdTime;
            return interview;
        }
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", createdTime=" + createdTime +
                ", scheduledTime=" + scheduledTime +
                ", applicant=" + applicant +
                ", interviewers=" + interviewers +
                '}';
    }
}
