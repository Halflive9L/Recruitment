package be.xplore.recruitment.domain.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
@Entity
@Table
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long applicantId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date dateOfBirth;

    @Column
    private String address;

    @Column
    private String education;

    @Column
    @Email
    private String email;

    @Column
    private String phone;

    public Applicant() {
    }

    public Applicant(Applicant applicant) {
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

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }
}
