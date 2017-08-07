package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;

import java.io.OutputStream;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class DownloadAttachmentResponseModel {
    private Attachment attachment;
    private OutputStream outputStream;

    DownloadAttachmentResponseModel(Attachment attachment, OutputStream outputStream) {
        this.attachment = attachment;
        this.outputStream = outputStream;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
