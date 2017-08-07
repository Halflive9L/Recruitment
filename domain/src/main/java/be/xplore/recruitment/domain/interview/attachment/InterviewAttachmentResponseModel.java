package be.xplore.recruitment.domain.interview.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public class InterviewAttachmentResponseModel {
    private Attachment attachment;

    InterviewAttachmentResponseModel(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }
}
