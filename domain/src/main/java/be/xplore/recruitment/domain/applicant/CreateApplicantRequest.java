package be.xplore.recruitment.domain.applicant;

import java.util.Date;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class CreateApplicantRequest {
    long applicantId;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String address;
    String education;
    String email;
    String phone;
}
