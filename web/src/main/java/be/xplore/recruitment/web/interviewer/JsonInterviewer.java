package be.xplore.recruitment.web.interviewer;

import be.xplore.recruitment.domain.interviewer.Interviewer;
import be.xplore.recruitment.domain.interviewer.InterviewerResponseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class JsonInterviewer {
    private long interviewerId;
    private String firstName;
    private String lastName;

    @JsonCreator
    public JsonInterviewer() {
    }

    public static JsonInterviewer asJsonInterviewer(InterviewerResponseModel response) {
        return builder()
                .withFirstName(response.getFirstName())
                .withLastName(response.getLastName())
                .withInterviewerId(response.getInterviewerId())
                .build();
    }

    public static JsonInterviewerBuilder builder() {
        return new JsonInterviewerBuilder();
    }

    @JsonProperty
    public long getInterviewerId() {
        return interviewerId;
    }

    @JsonProperty
    public void setInterviewerId(long interviewerId) {
        this.interviewerId = interviewerId;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Interviewer toInterviewer() {
        return Interviewer.builder()
                .withFirstName(getFirstName())
                .withLastName(getLastName())
                .withInterviewerId(getInterviewerId())
                .build();
    }

    public static final class JsonInterviewerBuilder {
        private long interviewerId;
        private String firstName;
        private String lastName;

        private JsonInterviewerBuilder() {
        }

        public static JsonInterviewerBuilder aJsonInterviewer() {
            return new JsonInterviewerBuilder();
        }

        public JsonInterviewerBuilder withInterviewerId(long interviewerId) {
            this.interviewerId = interviewerId;
            return this;
        }

        public JsonInterviewerBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public JsonInterviewerBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public JsonInterviewer build() {
            JsonInterviewer jsonInterviewer = new JsonInterviewer();
            jsonInterviewer.setInterviewerId(interviewerId);
            jsonInterviewer.setFirstName(firstName);
            jsonInterviewer.setLastName(lastName);
            return jsonInterviewer;
        }
    }
}
