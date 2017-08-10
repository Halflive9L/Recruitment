package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.domain.util.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Applicant {
    private long applicantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;
    private List<String> files;
    private Set<Tag> tags;

    public Applicant() {
    }

    public static ApplicantBuilder builder() {
        return ApplicantBuilder.anApplicant();
    }

    @Override
    public String toString() {
        return "Applicant{" + "applicantId=" + applicantId + ", firstName='" + firstName + '\'' + ", lastName='" +
                lastName + '\'' + ", dateOfBirth=" + dateOfBirth + ", address='" + address + '\'' + ", education='"
                + education + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + '}';
    }

    void validateApplicant() {
        if (!Validator.isNullOrEmpty(email) && !Validator.isValidEmail(email)) {
            throw new InvalidEmailException();
        }
        if (!Validator.isNullOrEmpty(phone) && !Validator.isValidPhone(phone)) {
            throw new InvalidPhoneException();
        }
        validateDateOfBirth();
    }

    private void validateDateOfBirth() {
        if (dateOfBirth != null && !Validator.isValidDate(dateOfBirth)) {
            throw new InvalidDateException();
        }
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

    public List<String> getFiles() {
        return files;
    }

    @Override
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Applicant)) {
            return false;
        }
        Applicant applicant = (Applicant) o;
        return applicantId == applicant.applicantId &&
                Objects.equals(firstName, applicant.firstName) &&
                Objects.equals(lastName, applicant.lastName) &&
                Objects.equals(dateOfBirth, applicant.dateOfBirth) &&
                Objects.equals(address, applicant.address) &&
                Objects.equals(education, applicant.education) &&
                Objects.equals(email, applicant.email) &&
                Objects.equals(phone, applicant.phone) &&
                Objects.equals(files, applicant.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicantId, firstName, lastName, dateOfBirth, address, education, email, phone, files);
    }
}
