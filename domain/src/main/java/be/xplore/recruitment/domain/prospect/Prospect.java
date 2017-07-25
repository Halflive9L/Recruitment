package be.xplore.recruitment.domain.prospect;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */

class Prospect {
    private long prospectId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    Prospect() {
    }

    Prospect(Prospect prospect) {
        this.firstName = prospect.firstName;
        this.lastName = prospect.lastName;
        this.email = prospect.email;
        this.phone = prospect.phone;
    }

    long getProspectId() {
        return prospectId;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
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
}