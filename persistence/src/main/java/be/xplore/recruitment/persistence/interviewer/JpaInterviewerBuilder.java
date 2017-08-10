package be.xplore.recruitment.persistence.interviewer;

public final class JpaInterviewerBuilder {
    private long interviewerId;
    private String firstName;
    private String lastName;
    private String email;

    private JpaInterviewerBuilder() {
    }

    public static JpaInterviewerBuilder aJpaInterviewer() {
        return new JpaInterviewerBuilder();
    }

    public JpaInterviewerBuilder withInterviewerId(long interviewerId) {
        this.interviewerId = interviewerId;
        return this;
    }

    public JpaInterviewerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public JpaInterviewerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public JpaInterviewerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public JpaInterviewer build() {
        JpaInterviewer jpaInterviewer = new JpaInterviewer();
        jpaInterviewer.setInterviewerId(interviewerId);
        jpaInterviewer.setFirstName(firstName);
        jpaInterviewer.setLastName(lastName);
        jpaInterviewer.setEmail(email);
        return jpaInterviewer;
    }
}
