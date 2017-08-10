package be.xplore.recruitment.domain.interviewer;

public final class CreateInterviewerRequestBuilder {
    private String firstName;
    private String lastName;
    private String email;

    private CreateInterviewerRequestBuilder() {
    }

    public static CreateInterviewerRequestBuilder aCreateInterviewerRequest() {
        return new CreateInterviewerRequestBuilder();
    }

    public CreateInterviewerRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateInterviewerRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateInterviewerRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateInterviewerRequest build() {
        CreateInterviewerRequest createInterviewerRequest = new CreateInterviewerRequest();
        createInterviewerRequest.setFirstName(firstName);
        createInterviewerRequest.setLastName(lastName);
        createInterviewerRequest.setEmail(email);
        return createInterviewerRequest;
    }
}
