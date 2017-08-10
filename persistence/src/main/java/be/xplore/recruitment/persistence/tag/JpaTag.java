package be.xplore.recruitment.persistence.tag;

import be.xplore.recruitment.domain.tag.Tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import static be.xplore.recruitment.persistence.tag.JpaTag.QUERY_FIND_ALL;
import static be.xplore.recruitment.persistence.tag.JpaTag.QUERY_FIND_BY_NAME;

/**
 * @author Stijn Schack
 * @since 8/10/2017
 */
@Entity
@Table(name = "Tag")
@NamedQueries({
        @NamedQuery(name = QUERY_FIND_ALL, query = "SELECT t FROM JpaTag t"),
        @NamedQuery(name = QUERY_FIND_BY_NAME, query = "SELECT t FROM JpaTag t WHERE t.tagName LIKE :tagName")

})
public class JpaTag {

    public static final String QUERY_FIND_ALL = "Tag.findAll";
    public static final String QUERY_FIND_BY_NAME = "Tag.findByName";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tagId;

    @Column(unique = true, nullable = false)
    private String tagName;

    public JpaTag() {
    }

    JpaTag(String tagName) {
        this.tagName = tagName;
    }

    public JpaTag(long tagId, String tagName) {
        this.tagId = tagId;
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