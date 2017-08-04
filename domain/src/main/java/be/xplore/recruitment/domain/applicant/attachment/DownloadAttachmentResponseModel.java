package be.xplore.recruitment.domain.applicant.attachment;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class DownloadAttachmentResponseModel {
    private String attachmentName;
    private InputStream inputStream;
    private OutputStream outputStream;

    public DownloadAttachmentResponseModel(String attachmentName, InputStream inputStream, OutputStream outputStream) {
        this.attachmentName = attachmentName;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
