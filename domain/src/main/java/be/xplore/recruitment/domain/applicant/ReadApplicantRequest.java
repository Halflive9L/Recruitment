package be.xplore.recruitment.domain.applicant;

import java.time.LocalDate;
import java.util.Collections;

import static be.xplore.recruitment.domain.applicant.Applicant.builder;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
public class ReadApplicantRequest {
    public long applicantId;
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String address;
    public String education;
    public String email;
    public String phone;

    Applicant toApplicant() {
        return builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withDateOfBirth(dateOfBirth)
                .withAddress(address)
                .withEducation(education)
                .withEmail(email)
                .withPhone(phone)
                .withTags(Collections.emptySet())
                .build();
    }
}
