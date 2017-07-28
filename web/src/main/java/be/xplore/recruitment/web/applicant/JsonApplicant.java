package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.Applicant;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.Date;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@JsonComponent
public class JsonApplicant {
    private long applicantId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;

    @JsonCreator
    public JsonApplicant() {
    }

    @JsonProperty
    long getApplicantId() {
        return applicantId;
    }

    @JsonProperty
    void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    @JsonProperty
    String getFirstName() {
        return firstName;
    }

    @JsonProperty
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    String getLastName() {
        return lastName;
    }

    @JsonProperty
    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty
    Date getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty
    void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty
    String getAddress() {
        return address;
    }

    @JsonProperty
    void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty
    String getEducation() {
        return education;
    }

    @JsonProperty
    void setEducation(String education) {
        this.education = education;
    }

    @JsonProperty
    String getEmail() {
        return email;
    }

    @JsonProperty
    void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    String getPhone() {
        return phone;
    }

    @JsonProperty
    void setPhone(String phone) {
        this.phone = phone;
    }

    boolean isEmpty() {
        return firstName == null &&
                lastName == null &&
                dateOfBirth == null &&
                address == null &&
                education == null &&
                email == null &&
                phone == null;
    }

    static JsonApplicant asJsonApplicant(Applicant a){
        JsonApplicant jsonApplicant = new JsonApplicant();
        jsonApplicant.setFirstName(a.getFirstName());
        jsonApplicant.setLastName(a.getLastName());
        jsonApplicant.setAddress(a.getAddress());
        jsonApplicant.setDateOfBirth(a.getDateOfBirth());
        jsonApplicant.setEducation(a.getEducation());
        jsonApplicant.setEmail(a.getEmail());
        jsonApplicant.setPhone(a.getPhone());
        jsonApplicant.setApplicantId(a.getApplicantId());
        return jsonApplicant;
    }

    @Override
    public String toString() {
        return "JsonApplicant{" +
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
}
