package be.xplore.recruitment.domain.applicant.file;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class DownloadFileRequest {
    private long applicantId;
    private String fileName;

    public DownloadFileRequest(long applicantId, String fileName) {
        this.applicantId = applicantId;
        this.fileName = fileName;
    }

    long getApplicantId() {
        return applicantId;
    }

    String getFileName() {
        return fileName;
    }
}
