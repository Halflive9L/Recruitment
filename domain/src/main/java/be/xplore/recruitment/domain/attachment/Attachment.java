package be.xplore.recruitment.domain.attachment;

import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class Attachment {
    private long attachmentId;
    private String attachmentName;
    private InputStream inputStream;

    public Attachment() {
    }

    public Attachment(long attachmentId, String attachmentName) {
        this.attachmentId = attachmentId;
        this.attachmentName = attachmentName;
    }

    public void setAttachmentId(long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
