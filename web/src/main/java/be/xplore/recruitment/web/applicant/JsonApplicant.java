package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.ApplicantResponseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String address;
    private String education;
    private String email;
    private String phone;

    @JsonCreator
    public JsonApplicant() {
    }

    static JsonApplicant asJsonApplicant(ApplicantResponseModel a) {
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

    @JsonProperty
    long getApplicantId() {
        return applicantId;
    }

    @JsonProperty
    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty
    public String getAddress() {
        return address;
    }

    @JsonProperty
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty
    public String getEducation() {
        return education;
    }

    @JsonProperty
    public void setEducation(String education) {
        this.education = education;
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

    boolean isEmpty() {
        return firstName == null &&
                lastName == null &&
                dateOfBirth == null &&
                address == null &&
                education == null &&
                email == null &&
                phone == null;
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
