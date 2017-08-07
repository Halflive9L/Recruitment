package be.xplore.recruitment.domain.interviewer;

public class UpdateInterviewerRequest {
    private long interviewerId;
    private String firstName;
    private String lastName;

    public UpdateInterviewerRequest(long interviewerId, String firstName, String lastName) {
        this.interviewerId = interviewerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
}
