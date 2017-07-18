package be.xplore.recruitment.model;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */
public class Prospect {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Prospect() {

    }

    public Prospect(int id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Prospect(int id, Prospect prospect) {
        this(id, prospect.getFirstName(), prospect.getLastName(), prospect.getEmail(), prospect.getPhone());
    }

    public int getId() {
        return id;
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