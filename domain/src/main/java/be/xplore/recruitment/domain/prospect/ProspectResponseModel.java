package be.xplore.recruitment.domain.prospect;

/**
 * @author Lander
 * @since 31/07/2017
 */
public class ProspectResponseModel {

    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    ProspectResponseModel(Prospect prospect) {
        this.prospectId = prospect.getProspectId();
        this.firstName = prospect.getFirstName();
        this.lastName = prospect.getLastName();
        this.email = prospect.getEmail();
        this.phone = prospect.getPhone();

    }


    public boolean isEmpty() {
        return prospectId == 0 &&
                isNullOrEmpty(firstName) &&
                isNullOrEmpty(lastName) &&
                isNullOrEmpty(email) &&
                isNullOrEmpty(phone);
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
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

    @Override
    public String toString() {
        return "ProspectResponseModel{" +
                "prospectId=" + prospectId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
