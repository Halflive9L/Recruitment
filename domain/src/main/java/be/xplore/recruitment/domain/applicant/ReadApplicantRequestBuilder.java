package be.xplore.recruitment.domain.applicant;

import java.time.LocalDate;

public final class ReadApplicantRequestBuilder {
    public long applicantId;
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;

    private ReadApplicantRequestBuilder() {
    }

    public static ReadApplicantRequestBuilder aReadApplicantRequest() {
        return new ReadApplicantRequestBuilder();
    }

    public ReadApplicantRequestBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public ReadApplicantRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ReadApplicantRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ReadApplicantRequestBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public ReadApplicantRequestBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ReadApplicantRequestBuilder withEducation(String education) {
        this.education = education;
        return this;
    }

    public ReadApplicantRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ReadApplicantRequestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ReadApplicantRequest build() {
        ReadApplicantRequest readApplicantRequest = new ReadApplicantRequest();
        readApplicantRequest.setApplicantId(applicantId);
        readApplicantRequest.setFirstName(firstName);
        readApplicantRequest.setLastName(lastName);
        readApplicantRequest.setDateOfBirth(dateOfBirth);
        readApplicantRequest.setAddress(address);
        readApplicantRequest.setEducation(education);
        readApplicantRequest.setEmail(email);
        readApplicantRequest.setPhone(phone);
        return readApplicantRequest;
    }
}
