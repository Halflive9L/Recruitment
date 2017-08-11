package be.xplore.recruitment.persistence.prospect;

import be.xplore.recruitment.domain.prospect.Prospect;
import be.xplore.recruitment.persistence.tag.JpaTag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lander
 * @since 26/07/2017
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "Prospect")
@NamedQueries({
        @NamedQuery(name = JpaProspect.QUERY_FIND_BY_ID,
                query = "SELECT p from JpaProspect p where p.prospectId = :prospectId"),
        @NamedQuery(name = JpaProspect.QUERY_FIND_ALL,
                query = "SELECT p from JpaProspect p"),
        @NamedQuery(name = JpaProspect.QUERY_DELETE,
                query = "DELETE FROM JpaProspect p where p.prospectId = :prospectId")})

public class JpaProspect {

    static final String QUERY_FIND_BY_ID = "Prospect.findProspectById";
    static final String QUERY_FIND_ALL = "Prospect.findAll";
    static final String QUERY_DELETE = "Prospect.deleteProspect";

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

    @ManyToMany(targetEntity = JpaTag.class, fetch = FetchType.LAZY)
    @JoinTable(name = "prospect_tag",
            joinColumns = @JoinColumn(name = "prospect_id", referencedColumnName = "prospectId"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tagId"))
    private Set<JpaTag> tags;

    public JpaProspect() {
    }

    Prospect toProspect() {
        return Prospect.builder()
                .withFirstName(this.getFirstName())
                .withLastName(this.getLastName())
                .withProspectId(this.getProspectId())
                .withEmail(this.getEmail())
                .withPhone(this.getPhone())
                .withTags(this.getTags().stream().map(JpaTag::toTag).collect(Collectors.toSet()))
                .build();
    }


    long getProspectId() {
        return prospectId;
    }

    public void setProspectId(long prospectId) {
        this.prospectId = prospectId;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
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

    public Set<JpaTag> getTags() {
        return tags;
    }

    public void setTags(Set<JpaTag> tags) {
        this.tags = tags;
    }
    static JpaProspect fromProspect(Prospect prospect) {
        return JpaProspectBuilder.aJpaProspect()
                .withProspectId(prospect.getProspectId())
                .withFirstName(prospect.getFirstName())
                .withLastName(prospect.getLastName())
                .withEmail(prospect.getEmail())
                .withPhone(prospect.getPhone())
                .withTags(prospect.getTags().stream().map(JpaTag::fromTag).collect(Collectors.toSet()))
                .build();
    }
}
