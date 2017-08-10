package be.xplore.recruitment.domain.interviewer;

public final class UpdateInterviewerRequestBuilder {
    private long interviewerId;
    private String firstName;
    private String lastName;
    private String email;

    private UpdateInterviewerRequestBuilder() {
    }

    public static UpdateInterviewerRequestBuilder anUpdateInterviewerRequest() {
        return new UpdateInterviewerRequestBuilder();
    }

    public UpdateInterviewerRequestBuilder withInterviewerId(long interviewerId) {
        this.interviewerId = interviewerId;
        return this;
    }

    public UpdateInterviewerRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UpdateInterviewerRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UpdateInterviewerRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UpdateInterviewerRequest build() {
        UpdateInterviewerRequest updateInterviewerRequest = new UpdateInterviewerRequest();
        updateInterviewerRequest.setInterviewerId(interviewerId);
        updateInterviewerRequest.setFirstName(firstName);
        updateInterviewerRequest.setLastName(lastName);
        updateInterviewerRequest.setEmail(email);
        return updateInterviewerRequest;
    }
}
