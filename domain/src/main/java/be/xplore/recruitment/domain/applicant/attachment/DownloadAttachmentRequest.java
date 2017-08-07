package be.xplore.recruitment.domain.applicant.attachment;

import java.io.OutputStream;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class DownloadAttachmentRequest {

    private long attachmentId;
    private OutputStream outputStream;

    public DownloadAttachmentRequest(long attachmentId, OutputStream outputStream) {
        this.attachmentId = attachmentId;
        this.outputStream = outputStream;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    OutputStream getOutputStream() {
        return outputStream;
    }
}
