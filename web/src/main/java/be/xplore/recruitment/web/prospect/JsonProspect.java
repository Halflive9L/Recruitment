package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.domain.prospect.ProspectResponseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.ResponseEntity;

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

    private JsonProspect(JsonProspectBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    static JsonProspect asJsonProspect(ProspectResponseModel responseModel) {
        JsonProspect jsonProspect = new JsonProspect();
        jsonProspect.setFirstName(responseModel.getFirstName());
        jsonProspect.setLastName(responseModel.getLastName());
        jsonProspect.setPhone(responseModel.getPhone());
        jsonProspect.setEmail(responseModel.getEmail());
        return jsonProspect;
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


    public static class JsonProspectBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public JsonProspectBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public JsonProspect build() {
            return new JsonProspect(this);
        }

        public JsonProspectBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public JsonProspectBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

    }
}
