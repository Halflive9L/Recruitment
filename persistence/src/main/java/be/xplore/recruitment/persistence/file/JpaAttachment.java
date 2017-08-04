package be.xplore.recruitment.persistence.file;

import be.xplore.recruitment.persistence.applicant.JpaApplicant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import static be.xplore.recruitment.persistence.file.JpaAttachment.QUERY_FIND_BY_ID;

/**
 * @author Stijn Schack
 * @since 8/3/2017
 */
@Entity
@Table(name = "file")
@NamedQueries({
        @NamedQuery(name = QUERY_FIND_BY_ID, query =
                "SELECT a FROM JpaAttachment a WHERE a.attachmentId = :attachmentId")
})
public class JpaAttachment {
    public static final String QUERY_FIND_BY_ID = "Attachment.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long attachmentId;

    @Column
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "APPLICANT_ATTACHMENT")
    private JpaApplicant applicant;


    public JpaAttachment() {

    }

    public JpaAttachment(JpaApplicant applicant, String fileName) {
        this.applicant = applicant;
        this.fileName = fileName;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    void setAttachmentId(long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
