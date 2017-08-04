package be.xplore.recruitment.persistence.interview;

import be.xplore.recruitment.domain.interview.Interview;
import be.xplore.recruitment.persistence.applicant.JpaApplicant;
import be.xplore.recruitment.persistence.interviewer.JpaInterviewer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Interview")
public class JpaInterview {
    @Column(name = "interview_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long interviewId;

    @Column
    private LocalDateTime createdTime;

    @Column
    private LocalDateTime scheduledTime;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "applicant_id")
    private JpaApplicant applicant;

    @ManyToMany(targetEntity = JpaInterviewer.class, fetch = FetchType.EAGER)
    @JoinTable(name = "interview_interviewers",
            joinColumns = @JoinColumn(name = "interview_id", referencedColumnName = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "interviewer_id", referencedColumnName = "interviewer_id"))
    private List<JpaInterviewer> interviewers;

    public JpaInterview() {
    }

    public long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(long interviewId) {
        this.interviewId = interviewId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public JpaApplicant getApplicant() {
        return applicant;
    }

    public void setApplicant(JpaApplicant applicant) {
        this.applicant = applicant;
    }

    public List<JpaInterviewer> getInterviewers() {
        return interviewers;
    }

    public void setInterviewers(List<JpaInterviewer> interviewers) {
        this.interviewers = interviewers;
    }

    public Interview toInterview() {
        return Interview.builder()
                .withApplicant(getApplicant().toApplicant())
                .withInterviewId(getInterviewId())
                .withScheduledTime(getScheduledTime())
                .withCreatedTime(getCreatedTime())
                .withInterviewers(getInterviewers().stream()
                        .map(JpaInterviewer::toInterviewer)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public String toString() {
        return "JpaInterview{" +
                "interviewId=" + interviewId +
                ", createdTime=" + createdTime +
                ", scheduledTime=" + scheduledTime +
                ", applicant=" + applicant +
                ", interviewers=" + interviewers +
                '}';
    }
}
