package be.xplore.recruitment.domain.interviewer;

public class CreateInterviewerRequest {
    private String firstName;
    private String lastName;

    public CreateInterviewerRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
