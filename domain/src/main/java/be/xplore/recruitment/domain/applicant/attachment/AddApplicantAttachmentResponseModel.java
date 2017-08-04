package be.xplore.recruitment.domain.applicant.attachment;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class AddApplicantAttachmentResponseModel {
    private String attachmentName;

    public AddApplicantAttachmentResponseModel(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentName() {
        return attachmentName;
    }
}
