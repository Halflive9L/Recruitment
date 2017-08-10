package be.xplore.recruitment.web.interviewer;

public final class JsonInterviewerBuilder {
    private long interviewerId;
    private String firstName;
    private String lastName;
    private String email;

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

    public JsonInterviewerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public JsonInterviewer build() {
        JsonInterviewer jsonInterviewer = new JsonInterviewer();
        jsonInterviewer.setInterviewerId(interviewerId);
        jsonInterviewer.setFirstName(firstName);
        jsonInterviewer.setLastName(lastName);
        jsonInterviewer.setEmail(email);
        return jsonInterviewer;
    }
}
