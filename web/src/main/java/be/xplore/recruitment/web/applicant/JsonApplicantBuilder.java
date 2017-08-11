package be.xplore.recruitment.web.applicant;

import java.time.LocalDate;
import java.util.Set;

public final class JsonApplicantBuilder {
    private long applicantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;
    private Set<String> tags;

    private JsonApplicantBuilder() {
    }

    public static JsonApplicantBuilder aJsonApplicant() {
        return new JsonApplicantBuilder();
    }

    public JsonApplicantBuilder withApplicantId(long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public JsonApplicantBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public JsonApplicantBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public JsonApplicantBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public JsonApplicantBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public JsonApplicantBuilder withEducation(String education) {
        this.education = education;
        return this;
    }

    public JsonApplicantBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    JsonApplicantBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    JsonApplicantBuilder withTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public JsonApplicant build() {
        JsonApplicant jsonApplicant = new JsonApplicant();
        jsonApplicant.setApplicantId(applicantId);
        jsonApplicant.setFirstName(firstName);
        jsonApplicant.setLastName(lastName);
        jsonApplicant.setDateOfBirth(dateOfBirth);
        jsonApplicant.setAddress(address);
        jsonApplicant.setEducation(education);
        jsonApplicant.setEmail(email);
        jsonApplicant.setPhone(phone);
        jsonApplicant.setTags(tags);
        return jsonApplicant;
    }
}
