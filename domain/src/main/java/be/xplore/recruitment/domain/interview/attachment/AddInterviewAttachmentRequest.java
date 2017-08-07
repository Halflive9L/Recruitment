package be.xplore.recruitment.domain.interview.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public class AddInterviewAttachmentRequest {
    private long interviewId;
    private Attachment attachment;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(long interviewId) {
        this.interviewId = interviewId;
    }

}
