package be.xplore.recruitment.domain.applicant;


import be.xplore.recruitment.domain.tag.Tag;

import java.time.LocalDate;
import java.util.Set;

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
    public Set<Tag> tags;
}
