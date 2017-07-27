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

    private Prospect(ProspectBuilder builder) {
        this.prospectId = builder.prospectId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
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


    public static ProspectBuilder builder(String firstName, String lastName) {
        return new ProspectBuilder(firstName, lastName);
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
        private long prospectId;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public ProspectBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Prospect build() {
            return new Prospect(this);
        }

        public ProspectBuilder withId(long id){
            this.prospectId = id;
            return this;
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