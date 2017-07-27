package be.xplore.recruitment.domain.applicant;


import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import java.util.Date;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;
import static be.xplore.recruitment.domain.util.Validator.isValidDate;
import static be.xplore.recruitment.domain.util.Validator.isValidEmail;
import static be.xplore.recruitment.domain.util.Validator.isValidPhone;

/**
 * @author Stijn Schack
 * @since 7/20/2017
 */
public class Applicant {
    private long applicantId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;

    private Applicant(ApplicantBuilder builder) {
        this.applicantId = builder.applicantId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.address = builder.address;
        this.education = builder.education;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantId=" + applicantId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", education='" + education + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static ApplicantBuilder builder() {
        return new ApplicantBuilder();
    }

    void validateApplicant() {
        if (!isNullOrEmpty(email) && !isValidEmail(email)) {
            throw new InvalidEmailException();
        }
        if (!isNullOrEmpty(phone) && !isValidPhone(phone)) {
            throw new InvalidPhoneException();
        }
        if (dateOfBirth != null && !isValidDate(dateOfBirth)) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    static class ApplicantBuilder {
        private long applicantId;
        private String firstName;
        private String lastName;
        private Date dateOfBirth;
        private String address;
        private String education;
        private String email;
        private String phone;

        public ApplicantBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public ApplicantBuilder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public ApplicantBuilder withId(long id) {
            this.applicantId = id;
            return this;
        }

        public ApplicantBuilder withDateOfBirth(Date dateOfBirth) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ApplicantBuilder builder = (ApplicantBuilder) o;

            if (applicantId != builder.applicantId) return false;
            if (firstName != null ? !firstName.equals(builder.firstName) : builder.firstName != null) return false;
            if (lastName != null ? !lastName.equals(builder.lastName) : builder.lastName != null) return false;
            if (dateOfBirth != null ? !dateOfBirth.equals(builder.dateOfBirth) : builder.dateOfBirth != null)
                return false;
            if (address != null ? !address.equals(builder.address) : builder.address != null) return false;
            if (education != null ? !education.equals(builder.education) : builder.education != null) return false;
            if (email != null ? !email.equals(builder.email) : builder.email != null) return false;
            return phone != null ? phone.equals(builder.phone) : builder.phone == null;
        }

        @Override
        public int hashCode() {
            int result = (int) (applicantId ^ (applicantId >>> 32));
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
            result = 31 * result + (address != null ? address.hashCode() : 0);
            result = 31 * result + (education != null ? education.hashCode() : 0);
            result = 31 * result + (email != null ? email.hashCode() : 0);
            result = 31 * result + (phone != null ? phone.hashCode() : 0);
            return result;
        }

        public Applicant build() {
            return new Applicant(this);
        }
    }
}
