package be.xplore.recruitment.domain.applicant;

import java.util.Date;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public class UpdateApplicantRequest {
    private final long applicantId;
    public String firstName;
    public String lastName;
    public Date dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;

    public UpdateApplicantRequest(long applicantId) {
        this.applicantId = applicantId;
    }

    long getApplicantId() {
        return applicantId;
    }
}