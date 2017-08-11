package be.xplore.recruitment.domain.prospect;

/**
 * @author Lander
 * @since 27/07/2017
 */
public class ReadProspectRequest {
    public long prospectId;
    public String firstName;
    public String lastName;
    public String phone;
    public String email;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    Prospect toProspect() {
        return Prospect.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPhone(phone)
                .build();
    }

}
