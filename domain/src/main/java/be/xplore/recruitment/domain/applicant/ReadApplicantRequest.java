package be.xplore.recruitment.domain.applicant;

import java.util.Date;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class ReadApplicantRequest {
    public long applicantId;
    public String firstName;
    public String lastName;
    public Date dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;
}
