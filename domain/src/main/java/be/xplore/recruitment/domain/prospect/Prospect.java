package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import static be.xplore.recruitment.domain.util.Validator.*;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */

public class Prospect {
    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    Prospect(long prospectId, Prospect prospect) {
        this(prospect.getFirstName(), prospect.getLastName(), prospect.getEmail(), prospect.getPhone());
        this.prospectId = prospectId;
    }

    private Prospect(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    void validateProspect() throws InvalidEmailException, InvalidPhoneException {
        if (!isNullOrEmpty(email) && !isValidEmail(email)) {
            throw new InvalidEmailException();
        }
        if (!isNullOrEmpty(phone) && !isValidPhone(phone)) {
            throw new InvalidPhoneException();
        }
    }

    long getProspectId() {
        return prospectId;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Prospect{" +
                "prospectId=" + prospectId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static class ProspectBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public ProspectBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Prospect createProspect() {
            return new Prospect(firstName, lastName, email, phone);
        }

        public ProspectBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ProspectBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }
    }
}