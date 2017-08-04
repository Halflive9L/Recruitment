package be.xplore.recruitment.domain.applicant.attachment;

import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class AddApplicantAttachmentRequest {
    private long applicantId;
    private InputStream input;
    private String attachmentName;

    String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
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
