package be.xplore.recruitment.domain.attachment;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
public class DeleteAttachmentRequest {
    private final long attachmentId;

    public DeleteAttachmentRequest(long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public long getAttachmentId() {
        return attachmentId;
    }
}
