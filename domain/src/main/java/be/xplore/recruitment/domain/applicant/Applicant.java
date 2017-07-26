package be.xplore.recruitment.domain.applicant;


import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import java.util.Date;

import static be.xplore.recruitment.domain.util.Validator.*;

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

    public Applicant(long applicantId, Applicant applicant) {
        this(applicant.getFirstName(), applicant.getLastName(),
                applicant.getDateOfBirth(),
                applicant.getAddress(), applicant.getEducation(),
                applicant.getEmail(), applicant.getPhone());
        this.applicantId = applicantId;
    }

    private Applicant(String firstName, String lastName,
                      Date dateOfBirth, String address,
                      String education, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.education = education;
        this.email = email;
        this.phone = phone;
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

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    static class ApplicantBuilder {
        private String firstName;
        private String lastName;
        private Date dateOfBirth;
        private String address;
        private String education;
        private String email;
        private String phone;

        public ApplicantBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public ApplicantBuilder setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public ApplicantBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public ApplicantBuilder setEducation(String education) {
            this.education = education;
            return this;
        }

        public ApplicantBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ApplicantBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Applicant createApplicant() {
            return new Applicant(firstName, lastName, dateOfBirth, address, education, email, phone);
        }
    }
}
