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
import javax.persistence.Table;

/**
 * @author Stijn Schack
 * @since 8/3/2017
 */
@Entity
@Table(name = "file")
public class JpaAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long fileId;

    @Column
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "APPLICANT_FILE")
    private JpaApplicant applicant;


    public JpaAttachment() {

    }

    public JpaAttachment(JpaApplicant applicant, String fileName) {
        this.applicant = applicant;
        this.fileName = fileName;
    }

    long getFileId() {
        return fileId;
    }

    void setFileId(long fileId) {
        this.fileId = fileId;
    }

    String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
