package be.xplore.recruitment.domain.applicant.attachment;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class ApplicantAttachmentResponseModel {
    private String attachmentName;

    public ApplicantAttachmentResponseModel(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentName() {
        return attachmentName;
    }
}
