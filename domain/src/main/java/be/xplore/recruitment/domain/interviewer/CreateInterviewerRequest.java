package be.xplore.recruitment.domain.interviewer;

public class CreateInterviewerRequest {
    private String firstName;
    private String lastName;
    private String email;

    public CreateInterviewerRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public static CreateInterviewerRequestBuilder builder() {
        return new CreateInterviewerRequestBuilder();
    }


    public static final class CreateInterviewerRequestBuilder {
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
            createInterviewerRequest.lastName = this.lastName;
            createInterviewerRequest.firstName = this.firstName;
            createInterviewerRequest.email = this.email;
            return createInterviewerRequest;
        }
    }
}
