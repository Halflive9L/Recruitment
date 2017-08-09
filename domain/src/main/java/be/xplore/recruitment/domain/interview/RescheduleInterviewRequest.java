package be.xplore.recruitment.domain.interview;

import java.time.LocalDateTime;

public class RescheduleInterviewRequest {
    private long interviewId;
    private String newLocation;
    private LocalDateTime newScheduledTime;

    public long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(long interviewId) {
        this.interviewId = interviewId;
    }

    public String getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }

    public LocalDateTime getNewScheduledTime() {
        return newScheduledTime;
    }

    public void setNewScheduledTime(LocalDateTime newScheduledTime) {
        this.newScheduledTime = newScheduledTime;
    }

    public static RescheduleInterviewRequestBuilder builder() {
        return new RescheduleInterviewRequestBuilder();
    }

    public static final class RescheduleInterviewRequestBuilder {
        private long interviewId;
        private String newLocation;
        private LocalDateTime newScheduledTime;

        private RescheduleInterviewRequestBuilder() {
        }

        public static RescheduleInterviewRequestBuilder aRescheduleInterviewRequest() {
            return new RescheduleInterviewRequestBuilder();
        }

        public RescheduleInterviewRequestBuilder withInterviewId(long interviewId) {
            this.interviewId = interviewId;
            return this;
        }

        public RescheduleInterviewRequestBuilder withNewLocation(String newLocation) {
            this.newLocation = newLocation;
            return this;
        }

        public RescheduleInterviewRequestBuilder withNewScheduledTime(LocalDateTime newScheduledTime) {
            this.newScheduledTime = newScheduledTime;
            return this;
        }

        public RescheduleInterviewRequest build() {
            RescheduleInterviewRequest rescheduleInterviewRequest = new RescheduleInterviewRequest();
            rescheduleInterviewRequest.setInterviewId(interviewId);
            rescheduleInterviewRequest.setNewLocation(newLocation);
            rescheduleInterviewRequest.setNewScheduledTime(newScheduledTime);
            return rescheduleInterviewRequest;
        }
    }
}
