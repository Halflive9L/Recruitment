package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class AddApplicantAttachmentRequest {
    private long applicantId;
    private Attachment attachment;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }
}
