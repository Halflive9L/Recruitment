package be.xplore.recruitment.domain.file;

import java.io.OutputStream;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class DownloadFileRequest {
    private long applicantId;
    private String fileName;
    private OutputStream responseStream;

    public DownloadFileRequest(long applicantId, String fileName, OutputStream responseStream) {
        this.applicantId = applicantId;
        this.fileName = fileName;
        this.responseStream = responseStream;
    }

    long getApplicantId() {
        return applicantId;
    }

    String getFileName() {
        return fileName;
    }

    public OutputStream getResponseStream() {
        return responseStream;
    }
}
