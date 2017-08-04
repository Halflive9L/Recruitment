package be.xplore.recruitment.web.interview;

import be.xplore.recruitment.domain.interview.InterviewResponseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDateTime;
import java.util.List;

@JsonComponent
public class JsonInterview {
    private long interviewId;
    private LocalDateTime createdTime;
    private LocalDateTime scheduledTime;
    private long applicantId;
    private List<Long> interviewerIds;

    @JsonCreator
    public JsonInterview() {
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
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @JsonProperty
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    @JsonProperty
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    @JsonProperty
    public long getApplicantId() {
        return applicantId;
    }

    @JsonProperty
    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    @JsonProperty
    public List<Long> getInterviewerIds() {
        return interviewerIds;
    }

    @JsonProperty
    public void setInterviewerIds(List<Long> interviewerIds) {
        this.interviewerIds = interviewerIds;
    }

    public static JsonInterviewBuilder builder() {
        return new JsonInterviewBuilder();
    }

    public static JsonInterview asJsonInterview(InterviewResponseModel response) {
        return JsonInterview.builder()
                .withApplicantId(response.getApplicantId())
                .withCreatedTime(response.getCreatedTime())
                .withScheduledTime(response.getScheduledTime())
                .withInterviewerIds(response.getInterviewerIds())
                .withInterviewId(response.getInterviewId())
                .build();
    }

    public static final class JsonInterviewBuilder {
        private long interviewId;
        private LocalDateTime createdTime;
        private LocalDateTime scheduledTime;
        private long applicantId;
        private List<Long> interviewerIds;

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

        public JsonInterview build() {
            JsonInterview jsonInterview = new JsonInterview();
            jsonInterview.setInterviewId(interviewId);
            jsonInterview.setCreatedTime(createdTime);
            jsonInterview.setScheduledTime(scheduledTime);
            jsonInterview.setApplicantId(applicantId);
            jsonInterview.setInterviewerIds(interviewerIds);
            return jsonInterview;
        }
    }

    @Override
    public String toString() {
        return "JsonInterview{" +
                "interviewId=" + interviewId +
                ", createdTime=" + createdTime +
                ", scheduledTime=" + scheduledTime +
                ", applicantId=" + applicantId +
                ", interviewerIds=" + interviewerIds +
                '}';
    }
}
