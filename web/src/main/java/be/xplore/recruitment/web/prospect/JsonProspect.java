package be.xplore.recruitment.web.prospect;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
class JsonProspect {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public JsonProspect() {
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
