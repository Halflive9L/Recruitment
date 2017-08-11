package be.xplore.recruitment.domain.applicant;

import java.time.LocalDate;

public final class CreateApplicantRequestBuilder {
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;

    private CreateApplicantRequestBuilder() {
    }

    public static CreateApplicantRequestBuilder aCreateApplicantRequest() {
        return new CreateApplicantRequestBuilder();
    }

    public CreateApplicantRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateApplicantRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateApplicantRequestBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public CreateApplicantRequestBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public CreateApplicantRequestBuilder withEducation(String education) {
        this.education = education;
        return this;
    }

    public CreateApplicantRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateApplicantRequestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CreateApplicantRequest build() {
        CreateApplicantRequest createApplicantRequest = new CreateApplicantRequest();
        createApplicantRequest.setFirstName(firstName);
        createApplicantRequest.setLastName(lastName);
        createApplicantRequest.setDateOfBirth(dateOfBirth);
        createApplicantRequest.setAddress(address);
        createApplicantRequest.setEducation(education);
        createApplicantRequest.setEmail(email);
        createApplicantRequest.setPhone(phone);
        return createApplicantRequest;
    }
}
