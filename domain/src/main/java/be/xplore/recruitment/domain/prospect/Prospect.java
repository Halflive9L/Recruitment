package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;

import static be.xplore.recruitment.domain.util.Validator.isNullOrEmpty;
import static be.xplore.recruitment.domain.util.Validator.isValidEmail;
import static be.xplore.recruitment.domain.util.Validator.isValidPhone;

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

    public static ProspectBuilder builder() {
        return new ProspectBuilder();
    }

    void validateProspect() throws InvalidEmailException, InvalidPhoneException {
        if (!isNullOrEmpty(email) && !isValidEmail(email)) {
            throw new InvalidEmailException();
        }
        if (!isNullOrEmpty(phone) && !isValidPhone(phone)) {
            throw new InvalidPhoneException();
        }
    }

    public long getProspectId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prospect prospect = (Prospect) o;

        if (prospectId != prospect.prospectId) {
            return false;
        }
        if (firstName != null ? !firstName.equals(prospect.firstName) : prospect.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(prospect.lastName) : prospect.lastName != null) {
            return false;
        }
        if (email != null ? !email.equals(prospect.email) : prospect.email != null) {
            return false;
        }
        return phone != null ? phone.equals(prospect.phone) : prospect.phone == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (prospectId ^ (prospectId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    public static class ProspectBuilder {
        private long prospectId;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public Prospect build() {
            return new Prospect(this);
        }

        public ProspectBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public ProspectBuilder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public ProspectBuilder withId(long id) {
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