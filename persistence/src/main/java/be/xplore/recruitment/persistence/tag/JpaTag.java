package be.xplore.recruitment.persistence.tag;

import be.xplore.recruitment.domain.tag.Tag;
import be.xplore.recruitment.persistence.applicant.JpaApplicant;
import be.xplore.recruitment.persistence.prospect.JpaProspect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Set;

import static be.xplore.recruitment.persistence.tag.JpaTag.QUERY_FIND_ALL;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
@Entity
@Table(name = "tag")
@NamedQueries(
        @NamedQuery(name = QUERY_FIND_ALL, query =
                "SELECT t FROM TAG t")
)
public class JpaTag {

    static final String QUERY_FIND_ALL = "Tag.findAll";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tagId;

    @Column(unique = true, nullable = false)
    private String tagName;

    @ManyToMany(mappedBy = "Applicant")
    private Set<JpaApplicant> applicants;

    @ManyToMany(mappedBy = "Prospect")
    private Set<JpaProspect> prospects;

    JpaTag(String tagName) {
        this.tagName = tagName;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    Tag toTag() {
        return new Tag(this.tagId, this.tagName);
    }
}