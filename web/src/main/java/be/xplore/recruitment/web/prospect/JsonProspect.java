package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ProspectResponseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@JsonComponent
class JsonProspect implements Serializable {
    private static final long serialVersionUID = -1155194210930940184L;
    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @JsonCreator
    public JsonProspect() {
    }

    static JsonProspect asJsonProspect(ProspectResponseModel responseModel) {
        JsonProspect jsonProspect = new JsonProspect();
        jsonProspect.setProspectId(responseModel.getProspectId());
        jsonProspect.setFirstName(responseModel.getFirstName());
        jsonProspect.setLastName(responseModel.getLastName());
        jsonProspect.setPhone(responseModel.getPhone());
        jsonProspect.setEmail(responseModel.getEmail());
        return jsonProspect;
    }

    @JsonProperty
    public long getProspectId() {
        return prospectId;
    }

    @JsonProperty
    public void setProspectId(long prospectId) {
        this.prospectId = prospectId;
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

    @JsonIgnore
    boolean isEmpty() {
        return firstName == null
                && lastName == null
                && email == null
                && phone == null;
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