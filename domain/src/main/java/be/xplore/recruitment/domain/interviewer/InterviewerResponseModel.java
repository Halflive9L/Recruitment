package be.xplore.recruitment.domain.interviewer;

public class InterviewerResponseModel {
    private long interviewerId;
    private String firstName;
    private String lastName;

    public InterviewerResponseModel(Interviewer interviewer) {
        this.interviewerId = interviewer.getInterviewerId();
        this.firstName = interviewer.getFirstName();
        this.lastName = interviewer.getLastName();
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
