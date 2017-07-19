package be.xplore.recruitment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Stijn Schack
 * @since 7/18/2017
 */

@Entity
@Table(name = "Prospect")
public class Prospect {
    @Id
    @Column
    private long prospectId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String phone;


    public Prospect(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Prospect() {
    }

    public Prospect(Prospect prospect) {
        this(prospect.getFirstName(), prospect.getLastName(), prospect.getEmail(), prospect.getPhone());
    }

    public long getProspectId() {
        return prospectId;
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