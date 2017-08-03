package be.xplore.recruitment.domain.applicant;

import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class CreateFileRequest {
    private long applicantId;
    private InputStream input;
    private String extension;

    public void setExtension(String extension) {
        this.extension = extension;
    }

    String getExtension() {
        return extension;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    InputStream getInput() {
        return input;
    }
}
