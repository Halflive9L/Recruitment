package be.xplore.recruitment.domain.applicant;

import java.io.InputStream;

/**
 * @author Stijn Schack
 * @since 8/2/2017
 */
public class CreateFileRequest {
    private long applicantId;
    private InputStream input;

    public CreateFileRequest(long applicantId, InputStream input) {
        this.applicantId = applicantId;
        this.input = input;
    }

    public long getApplicantId() {
        return applicantId;
    }

    InputStream getInput() {
        return input;
    }
}
