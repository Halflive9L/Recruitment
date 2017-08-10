package be.xplore.recruitment.domain.applicant;

import java.time.LocalDate;

public final class ApplicantBuilder {
    private long applicantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;

    private ApplicantBuilder() {
    }

    public static ApplicantBuilder anApplicant() {
        return new ApplicantBuilder();
    }

    public ApplicantBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public ApplicantBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ApplicantBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ApplicantBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public ApplicantBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ApplicantBuilder withEducation(String education) {
        this.education = education;
        return this;
    }

    public ApplicantBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ApplicantBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Applicant build() {
        Applicant applicant = new Applicant();
        applicant.setApplicantId(applicantId);
        applicant.setFirstName(firstName);
        applicant.setLastName(lastName);
        applicant.setDateOfBirth(dateOfBirth);
        applicant.setAddress(address);
        applicant.setEducation(education);
        applicant.setEmail(email);
        applicant.setPhone(phone);
        return applicant;
    }
}
