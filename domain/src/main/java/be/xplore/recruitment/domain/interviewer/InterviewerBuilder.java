package be.xplore.recruitment.domain.interviewer;

public final class InterviewerBuilder {
    private long interviewerId;
    private String firstName;
    private String lastName;
    private String email;

    private InterviewerBuilder() {
    }

    public static InterviewerBuilder anInterviewer() {
        return new InterviewerBuilder();
    }

    public InterviewerBuilder withInterviewerId(long interviewerId) {
        this.interviewerId = interviewerId;
        return this;
    }

    public InterviewerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public InterviewerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public InterviewerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Interviewer build() {
        Interviewer interviewer = new Interviewer();
        interviewer.setInterviewerId(interviewerId);
        interviewer.setFirstName(firstName);
        interviewer.setLastName(lastName);
        interviewer.setEmail(email);
        return interviewer;
    }
}
