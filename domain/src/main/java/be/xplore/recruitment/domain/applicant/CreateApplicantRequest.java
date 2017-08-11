package be.xplore.recruitment.domain.applicant;


import java.time.LocalDate;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class CreateApplicantRequest {
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;
}
