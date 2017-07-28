package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public class UpdateApplicantTest {
    private UpdateApplicant useCase;
    private Applicant[] mockApplicants;


    @Before
    public void initUseCase() {
        MockApplicantRepo repository = new MockApplicantRepo();
        mockApplicants = repository.mockApplicants;
        useCase = new UpdateApplicantUseCase(repository);
    }

    @Test
    public void testUpdateApplicant() {
        useCase.updateApplicant(getRequestFromApplicant(getValidApplicant()), applicant -> {
        });
        assertEquals(getExpectedApplicant(), mockApplicants[0]);
    }

    @Test(expected = InvalidPhoneException.class)
    public void testUpdateApplicantWithInvalidPhone() {
        useCase.updateApplicant(getRequestFromApplicant(getApplicantWithInvalidPhone()), applicant -> {
        });
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNonExistingApplicant() {
        useCase.updateApplicant(getRequestFromApplicant(Applicant.builder().withId(500).build()), applicant -> {
        });
    }

    @Ignore
    private UpdateApplicantRequest getRequestFromApplicant(Applicant applicant) {
        UpdateApplicantRequest request = new UpdateApplicantRequest(applicant.getApplicantId());
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        return request;
    }

    @Ignore
    private Applicant getValidApplicant() {
        return Applicant.builder()
                .withId(1)
                .withAddress("Mortsel")
                .withPhone("+32452148963").build();
    }

    @Ignore
    private Applicant getApplicantWithInvalidPhone() {
        return Applicant.builder()
                .withId(1)
                .withPhone("a")
                .build();
    }

    @Ignore
    private Applicant getExpectedApplicant() {
        return Applicant.builder()
                .withFirstName("John").withLastName("Smith").withId(1)
                .withDateOfBirth(new Calendar.Builder().setDate(1996, 10, 3).build().getTime())
                .withAddress("Mortsel")
                .withEducation("College")
                .withEmail("john.smith@example.com")
                .withPhone("+32452148963").build();
    }
}
