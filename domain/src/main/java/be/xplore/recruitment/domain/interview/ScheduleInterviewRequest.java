package be.xplore.recruitment.domain.interview;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleInterviewRequest {
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private long applicantId;
    private List<Long> interviewerIds;
    private boolean preInterviewReminderSent;

    public ScheduleInterviewRequest() {
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

    public boolean isPreInterviewReminderSent() {
        return preInterviewReminderSent;
    }



    public static ScheduleInterviewRequestBuilder builder() {
        return new ScheduleInterviewRequestBuilder();
    }

    public static final class ScheduleInterviewRequestBuilder {
        private LocalDateTime createdTime;
        private LocalDateTime scheduledTime;
        private long applicantId;
        private List<Long> interviewerIds;
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

        public ScheduleInterviewRequestBuilder withPreInterviewReminderSent(boolean sent) {
            this.preInterviewReminderSent = sent;
            return this;
        }

        public ScheduleInterviewRequest build() {
            ScheduleInterviewRequest scheduleInterviewRequest = new ScheduleInterviewRequest();
            scheduleInterviewRequest.createdTime = this.createdTime;
            scheduleInterviewRequest.interviewerIds = this.interviewerIds;
            scheduleInterviewRequest.applicantId = this.applicantId;
            scheduleInterviewRequest.scheduledTime = this.scheduledTime;
            scheduleInterviewRequest.preInterviewReminderSent = this.preInterviewReminderSent;
            return scheduleInterviewRequest;
        }
    }
}
