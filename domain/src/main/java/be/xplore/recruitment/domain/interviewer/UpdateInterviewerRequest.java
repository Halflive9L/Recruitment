package be.xplore.recruitment.domain.interviewer;

public class UpdateInterviewerRequest {
    private long interviewerId;
    private String firstName;
    private String lastName;
    private String email;

    public UpdateInterviewerRequest() {
    }

    public long getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(long interviewerId) {
        this.interviewerId = interviewerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UpdateInterviewerRequestBuilder builder() {
        return new UpdateInterviewerRequestBuilder();
    }

    public static final class UpdateInterviewerRequestBuilder {
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
}

