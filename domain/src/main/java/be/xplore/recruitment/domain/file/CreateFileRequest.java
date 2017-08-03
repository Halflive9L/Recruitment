package be.xplore.recruitment.domain.file;

import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class CreateFileRequest {
    private long applicantId;
    private InputStream input;
    private String extension;

    String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }
}
