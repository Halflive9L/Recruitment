package be.xplore.recruitment.domain.prospect;

/**
 * @author Lander
 * @since 26/07/2017
 */

public class CreateProspectRequest {
    public long prospectId;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;

    public CreateProspectRequest() {
    }

    public static CreateProspectRequestBuilder builder() {
        return CreateProspectRequestBuilder.aCreateProspectRequest();
    }

    public long getProspectId() {
        return prospectId;
    }

    public void setProspectId(long prospectId) {
        this.prospectId = prospectId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
