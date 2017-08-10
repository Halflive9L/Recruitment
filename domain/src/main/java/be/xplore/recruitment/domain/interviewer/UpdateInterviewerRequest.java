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
        return UpdateInterviewerRequestBuilder.anUpdateInterviewerRequest();
    }
}

