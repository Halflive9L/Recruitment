package be.xplore.recruitment.domain.file;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class ReadAllFilesForApplicantRequest {
    private long applicantId;

    public ReadAllFilesForApplicantRequest(long applicantId) {
        this.applicantId = applicantId;
    }

    public long getApplicantId() {
        return applicantId;
    }
}
