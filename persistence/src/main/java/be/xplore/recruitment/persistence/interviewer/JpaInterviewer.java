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

    public Interviewer toInterviewer() {
        return Interviewer.builder()
                .withInterviewerId(getInterviewerId())
                .withFirstName(getFirstName())
                .withLastName(getLastName())
                .build();
    }

    public static JpaInterviewer fromInterviewer(Interviewer interviewer) {
        JpaInterviewer e = new JpaInterviewer();
        e.setFirstName(interviewer.getFirstName());
        e.setLastName(interviewer.getLastName());
        e.setInterviewerId(interviewer.getInterviewerId());
        return e;
    }
}
