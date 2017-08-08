package be.xplore.recruitment.domain.interviewer;

import java.util.Objects;

public class Interviewer {
    private long interviewerId;
    private String firstName;
    private String lastName;
    private String email;

    public Interviewer() {
    }

    public Interviewer(long interviewerId, String firstName, String lastName) {
        this.interviewerId = interviewerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static InterviewerBuilder builder() {
        return new InterviewerBuilder();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Interviewer)) {
            return false;
        }
        Interviewer that = (Interviewer) o;
        return interviewerId == that.interviewerId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewerId, firstName, lastName);
    }

    public static final class InterviewerBuilder {
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
}
