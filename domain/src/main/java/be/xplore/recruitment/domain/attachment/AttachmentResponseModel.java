package be.xplore.recruitment.domain.attachment;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class AttachmentResponseModel {
    private Attachment attachment;

    public AttachmentResponseModel(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }
}
