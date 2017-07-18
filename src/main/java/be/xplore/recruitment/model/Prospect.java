package be.xplore.recruitment.model;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */
public class Prospect {
    private final int id;
    private String name;
    private String email;
    private String phone;

    public Prospect(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return String.format("{\"id\":%d,\"name\":%s,\"email\":%s,\"phone\":%s}", id, name, email, phone);
    }
}