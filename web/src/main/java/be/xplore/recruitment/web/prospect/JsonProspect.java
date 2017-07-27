package be.xplore.recruitment.web.prospect;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@JsonComponent
class JsonProspect implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @JsonCreator
    public JsonProspect() {
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public String getPhone() {
        return phone;
    }

    @JsonProperty
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "JsonProspect{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
