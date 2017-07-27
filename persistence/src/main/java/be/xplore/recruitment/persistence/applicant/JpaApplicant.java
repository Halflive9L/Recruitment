package be.xplore.recruitment.persistence.applicant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Lander on 26/07/2017.
 */

@Entity
@Table(name = "Applicant")
@NamedQueries({
        @NamedQuery(name = JpaApplicant.QUERY_FIND_BY_ID,
                query = "SELECT a from JpaApplicant a where a.applicantId = :applicantId")})
//,
//@NamedQuery(name = JpaApplicant.QUERY_FIND_ALL, query = "SELECT a from JpaApplicant a")})

public class JpaApplicant {

    static final String QUERY_FIND_BY_ID = "Applicant.findApplicantById";
    //static final String QUERY_FIND_ALL = "Applicant.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long applicantId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date dateOfBirth;

    @Column
    private String address;

    @Column
    private String education;

    @Column
    private String email;

    @Column
    private String phone;

    public JpaApplicant(JpaApplicant applicant) {
        this.firstName = applicant.firstName;
        this.lastName = applicant.lastName;
        this.dateOfBirth = applicant.dateOfBirth;
        this.address = applicant.address;
        this.education = applicant.education;
        this.email = applicant.email;
        this.phone = applicant.phone;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

}
