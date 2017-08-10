package be.xplore.recruitment.persistence.interviewer;

import be.xplore.recruitment.domain.interviewer.Interviewer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "Interviewer")
public class JpaInterviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interviewer_id")
    private long interviewerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    public JpaInterviewer() {
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

    public Interviewer toInterviewer() {
        return Interviewer.builder()
                .withInterviewerId(getInterviewerId())
                .withFirstName(getFirstName())
                .withLastName(getLastName())
                .withEmail(getEmail())
                .build();
    }

    public static JpaInterviewer fromInterviewer(Interviewer interviewer) {
        return JpaInterviewerBuilder.aJpaInterviewer()
                .withInterviewerId(interviewer.getInterviewerId())
                .withFirstName(interviewer.getFirstName())
                .withLastName(interviewer.getLastName())
                .withEmail(interviewer.getEmail())
                .build();
    }
}
