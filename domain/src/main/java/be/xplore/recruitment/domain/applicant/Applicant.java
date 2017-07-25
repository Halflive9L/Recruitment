package be.xplore.recruitment.domain.applicant;


import java.util.Date;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
class Applicant {
    private long applicantId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;

    Applicant(Applicant applicant) {
        this.firstName = applicant.firstName;
        this.lastName = applicant.lastName;
        this.dateOfBirth = applicant.dateOfBirth;
        this.address = applicant.address;
        this.education = applicant.education;
        this.email = applicant.email;
        this.phone = applicant.phone;
    }

    long getApplicantId() {
        return applicantId;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    Date getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    String getEducation() {
        return education;
    }

    void setEducation(String education) {
        this.education = education;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }
}
