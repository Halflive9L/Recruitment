package be.xplore.recruitment.domain.applicant;


import java.util.Date;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public class Applicant {
    private long applicantId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String education;
    private String email;
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
