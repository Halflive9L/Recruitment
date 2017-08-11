package be.xplore.recruitment.domain.applicant;

import java.time.LocalDate;

import static be.xplore.recruitment.domain.applicant.Applicant.builder;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class ReadApplicantRequest {
    public long applicantId;
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;

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

    Applicant toApplicant() {
        return builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withDateOfBirth(dateOfBirth)
                .withAddress(address)
                .withEducation(education)
                .withEmail(email)
                .withPhone(phone)
                .build();
    }
}
