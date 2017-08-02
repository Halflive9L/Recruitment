package be.xplore.recruitment.domain.applicant;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class GetAllFilesForApplicantRequest {
    private long applicantId;

    public GetAllFilesForApplicantRequest(long applicantId) {
        this.applicantId = applicantId;
    }

    public long getApplicantId() {
        return applicantId;
    }
}
