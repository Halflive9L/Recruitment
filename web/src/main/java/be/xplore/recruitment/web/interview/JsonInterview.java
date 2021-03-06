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
    private boolean cancelled;
    private String location;
    private boolean preInterviewReminderSent;

    @JsonCreator
    public JsonInterview() {
    }

    public static JsonInterviewBuilder builder() {
        return JsonInterviewBuilder.aJsonInterview();
    }

    public static JsonInterview asJsonInterview(InterviewResponseModel response) {
        return JsonInterview.builder()
                .withApplicantId(response.getApplicantId())
                .withCreatedTime(response.getCreatedTime())
                .withScheduledTime(response.getScheduledTime())
                .withInterviewerIds(response.getInterviewerIds())
                .withInterviewId(response.getInterviewId())
                .withCancelled(response.isCancelled())
                .withLocation(response.getLocation())
                .withPreInterviewReminderSent(response.isPreInterviewReminderSent())
                .build();
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

    @JsonProperty
    public boolean isCancelled() {
        return cancelled;
    }

    @JsonProperty
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @JsonProperty
    public String getLocation() {
        return location;
    }

    @JsonProperty
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty
    public boolean isPreInterviewReminderSent() {
        return preInterviewReminderSent;
    }

    @JsonProperty
    public void setPreInterviewReminderSent(boolean preInterviewReminderSent) {
        this.preInterviewReminderSent = preInterviewReminderSent;
    }

    @Override
    public String toString() {
        return "JsonInterview{" +
                "interviewId=" + interviewId +
                ", createdTime=" + createdTime +
                ", scheduledTime=" + scheduledTime +
                ", applicantId=" + applicantId +
                ", interviewerIds=" + interviewerIds +
                ", cancelled=" + cancelled +
                ", location='" + location + '\'' +
                '}';
    }
}
