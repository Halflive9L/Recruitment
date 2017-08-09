package be.xplore.recruitment.web.interview;

import be.xplore.recruitment.domain.interview.RescheduleInterviewRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDateTime;

@JsonComponent
public class JsonRescheduleRequest {
    private long interviewId;
    private LocalDateTime newScheduledTime;
    private String newLocation;

    @JsonCreator
    public JsonRescheduleRequest() {
    }

    @JsonProperty
    public long getInterviewId() {
        return interviewId;
    }

    @JsonProperty
    public void setInterviewId(long interviewId) {
        this.interviewId = interviewId;
    }

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getNewScheduledTime() {
        return newScheduledTime;
    }

    @JsonProperty
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public void setNewScheduledTime(LocalDateTime newScheduledTime) {
        this.newScheduledTime = newScheduledTime;
    }

    @JsonProperty
    public String getNewLocation() {
        return newLocation;
    }

    @JsonProperty
    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }

    public RescheduleInterviewRequest toRequest() {
        return RescheduleInterviewRequest.builder()
                .withInterviewId(getInterviewId())
                .withNewLocation(getNewLocation())
                .withNewScheduledTime(getNewScheduledTime())
                .build();
    }

    public static final class JsonRescheduleRequestBuilder {
        private long interviewId;
        private LocalDateTime newScheduledTime;
        private String newLocation;

        private JsonRescheduleRequestBuilder() {
        }

        public static JsonRescheduleRequestBuilder aJsonRescheduleRequest() {
            return new JsonRescheduleRequestBuilder();
        }

        public JsonRescheduleRequestBuilder withInterviewId(long interviewId) {
            this.interviewId = interviewId;
            return this;
        }

        public JsonRescheduleRequestBuilder withNewScheduledTime(LocalDateTime newScheduledTime) {
            this.newScheduledTime = newScheduledTime;
            return this;
        }

        public JsonRescheduleRequestBuilder withNewLocation(String newLocation) {
            this.newLocation = newLocation;
            return this;
        }

        public JsonRescheduleRequest build() {
            JsonRescheduleRequest jsonRescheduleRequest = new JsonRescheduleRequest();
            jsonRescheduleRequest.setInterviewId(interviewId);
            jsonRescheduleRequest.setNewScheduledTime(newScheduledTime);
            jsonRescheduleRequest.setNewLocation(newLocation);
            return jsonRescheduleRequest;
        }
    }
}
