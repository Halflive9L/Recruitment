package be.xplore.recruitment.domain.applicant;


import java.time.LocalDate;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public class ApplicantResponseModel {
    private long applicantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;

    ApplicantResponseModel(Applicant applicant) {
        this.applicantId = applicant.getApplicantId();
        this.firstName = applicant.getFirstName();
        this.lastName = applicant.getLastName();
        this.dateOfBirth = applicant.getDateOfBirth();
        this.address = applicant.getAddress();
        this.education = applicant.getEducation();
        this.email = applicant.getEmail();
        this.phone = applicant.getPhone();
    }

    public boolean isEmpty() {
        return applicantId == 0 &&
                isNullOrEmpty(firstName) &&
                isNullOrEmpty(lastName) &&
                dateOfBirth == null &&
                isNullOrEmpty(address) &&
                isNullOrEmpty(education) &&
                isNullOrEmpty(email) &&
                isNullOrEmpty(phone);
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
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
}
