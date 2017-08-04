package be.xplore.recruitment.domain.applicant.attachment;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class ListAllAttachmentsForApplicantRequest {
    private long applicantId;

    public ListAllAttachmentsForApplicantRequest(long applicantId) {
        this.applicantId = applicantId;
    }

    public long getApplicantId() {
        return applicantId;
    }
}
