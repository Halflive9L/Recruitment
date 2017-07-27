package be.xplore.recruitment.persistence.prospect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by Lander on 26/07/2017.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "Prospect")
@NamedQueries({
        @NamedQuery(name = JpaProspect.QUERY_FIND_BY_ID,
                query = "SELECT p from JpaProspect p where p.prospectId = :prospectId"),
        @NamedQuery(name = JpaProspect.QUERY_FIND_ALL,
                query = "SELECT p from JpaProspect p")})

public class JpaProspect {

    public static final String QUERY_FIND_BY_ID = "Prospect.findProspectById";
    public static final String QUERY_FIND_ALL = "Prospect.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long prospectId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    public JpaProspect() {
    }

    JpaProspect(JpaProspect jpaProspect) {
        this.firstName = jpaProspect.firstName;
        this.lastName = jpaProspect.lastName;
        this.email = jpaProspect.email;
        this.phone = jpaProspect.phone;
    }

    long getProspectId() {
        return prospectId;
    }

    String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String getPhone() {
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
