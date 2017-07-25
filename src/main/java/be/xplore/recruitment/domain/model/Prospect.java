package be.xplore.recruitment.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Prospect() {
    }

    public Prospect(Prospect prospect) {
        this.firstName = prospect.firstName;
        this.lastName = prospect.lastName;
        this.email = prospect.email;
        this.phone = prospect.phone;
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