package be.xplore.recruitment.persistence.applicant;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.persistence.attachment.JpaAttachment;
import be.xplore.recruitment.persistence.tag.JpaTag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Lander on 26/07/2017.
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "Applicant")
@NamedQueries({
        @NamedQuery(name = JpaApplicant.QUERY_FIND_BY_ID,
                query = "SELECT a from JpaApplicant a where a.applicantId = :applicantId"),
        @NamedQuery(name = JpaApplicant.QUERY_FIND_ALL, query = "SELECT a from JpaApplicant a"),
        @NamedQuery(name = JpaApplicant.QUERY_DELETE,
                query = "DELETE FROM JpaApplicant a WHERE a.applicantId = :applicantId")})

public class JpaApplicant {
    static final String QUERY_FIND_BY_ID = "Applicant.findApplicantById";
    static final String QUERY_FIND_ALL = "Applicant.findAll";
    static final String QUERY_DELETE = "Applicant.deleteApplicant";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long applicantId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private String address;
    @Column
    private String education;
    @Column
    private String email;
    @Column
    private String phone;
    @ManyToMany(targetEntity = JpaTag.class, fetch = FetchType.LAZY)
    @JoinTable(name = "applicant_tag",
            joinColumns = @JoinColumn(name = "applicant_id", referencedColumnName = "applicantId"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tagId"))
    private Set<JpaTag> tags;
    @OneToMany(mappedBy = "applicant")
    private Set<JpaAttachment> attachments;

    public JpaApplicant() {
    }

    public Applicant toApplicant() {
        return Applicant.builder()
                .withApplicantId(this.getApplicantId())
                .withFirstName(this.getFirstName())
                .withLastName(this.getLastName())
                .withAddress(this.getAddress())
                .withEducation(this.getEducation())
                .withDateOfBirth(this.getDateOfBirth())
                .withEmail(this.getEmail())
                .withPhone(this.getPhone())
                .withTags(this.getTags().stream().map(JpaTag::toTag).collect(Collectors.toSet()))
                .build();
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<JpaAttachment> getAttachments() {
        return attachments;
    }

    public void setTags(Set<JpaTag> tags) {
        this.tags = tags;
    }

    public Set<JpaTag> getTags() {
        return tags;
    }

    static JpaApplicant fromApplicant(Applicant applicant) {
        return JpaApplicantBuilder.aJpaApplicant()
                .withApplicantId(applicant.getApplicantId())
                .withFirstName(applicant.getFirstName())
                .withLastName(applicant.getLastName())
                .withEmail(applicant.getEmail())
                .withPhone(applicant.getPhone())
                .withDateOfBirth(applicant.getDateOfBirth())
                .withAddress(applicant.getAddress())
                .withEducation(applicant.getEducation())
                .withTags(applicant.getTags().stream().map(JpaTag::fromTag).collect(Collectors.toSet()))
                .build();
    }
}