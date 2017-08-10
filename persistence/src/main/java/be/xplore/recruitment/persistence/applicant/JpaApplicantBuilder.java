package be.xplore.recruitment.persistence.applicant;

import java.time.LocalDate;

public final class JpaApplicantBuilder {
    private long applicantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;

    private JpaApplicantBuilder() {
    }

    public static JpaApplicantBuilder aJpaApplicant() {
        return new JpaApplicantBuilder();
    }

    public JpaApplicantBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public JpaApplicantBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public JpaApplicantBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public JpaApplicantBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public JpaApplicantBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public JpaApplicantBuilder withEducation(String education) {
        this.education = education;
        return this;
    }

    public JpaApplicantBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public JpaApplicantBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public JpaApplicant build() {
        JpaApplicant jpaApplicant = new JpaApplicant();
        jpaApplicant.setApplicantId(applicantId);
        jpaApplicant.setFirstName(firstName);
        jpaApplicant.setLastName(lastName);
        jpaApplicant.setDateOfBirth(dateOfBirth);
        jpaApplicant.setAddress(address);
        jpaApplicant.setEducation(education);
        jpaApplicant.setEmail(email);
        jpaApplicant.setPhone(phone);
        return jpaApplicant;
    }
}
