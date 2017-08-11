package be.xplore.recruitment.domain.applicant;

import java.time.LocalDate;

public final class UpdateApplicantRequestBuilder {
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;
    private long applicantId;

    private UpdateApplicantRequestBuilder() {
    }

    public static UpdateApplicantRequestBuilder anUpdateApplicantRequest() {
        return new UpdateApplicantRequestBuilder();
    }

    public UpdateApplicantRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UpdateApplicantRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UpdateApplicantRequestBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UpdateApplicantRequestBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public UpdateApplicantRequestBuilder withEducation(String education) {
        this.education = education;
        return this;
    }

    public UpdateApplicantRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UpdateApplicantRequestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UpdateApplicantRequestBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public UpdateApplicantRequest build() {
        UpdateApplicantRequest updateApplicantRequest = new UpdateApplicantRequest(applicantId);
        updateApplicantRequest.setFirstName(firstName);
        updateApplicantRequest.setLastName(lastName);
        updateApplicantRequest.setDateOfBirth(dateOfBirth);
        updateApplicantRequest.setAddress(address);
        updateApplicantRequest.setEducation(education);
        updateApplicantRequest.setEmail(email);
        updateApplicantRequest.setPhone(phone);
        return updateApplicantRequest;
    }
}
