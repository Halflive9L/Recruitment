package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.ApplicantResponseModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDate;

@JsonComponent
public class JsonApplicant {
    private long applicantId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
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
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public void setDateOfBirth(LocalDate dateOfBirth) {
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
