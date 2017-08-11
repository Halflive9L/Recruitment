package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.tag.Tag;

import java.util.Objects;
import java.util.Set;

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
    private Set<Tag> tags;

    public Prospect() {
    }

    public static ProspectBuilder builder() {
        return ProspectBuilder.aProspect();
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

    public void setProspectId(long prospectId) {
        this.prospectId = prospectId;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prospect)) {
            return false;
        }
        Prospect prospect = (Prospect) o;
        return prospectId == prospect.prospectId &&
                Objects.equals(firstName, prospect.firstName) &&
                Objects.equals(lastName, prospect.lastName) &&
                Objects.equals(email, prospect.email) &&
                Objects.equals(phone, prospect.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prospectId, firstName, lastName, email, phone);
    }
}