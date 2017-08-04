package be.xplore.recruitment.domain.applicant.attachment;

import java.io.OutputStream;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class DownloadAttachmentRequest {

    private long applicantId;
    private String fileName;
    private OutputStream outputStream;

    public DownloadAttachmentRequest(long applicantId, String fileName, OutputStream outputStream) {
        this.applicantId = applicantId;
        this.fileName = fileName;
        this.outputStream = outputStream;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public String getAttachmentName() {
        return fileName;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
