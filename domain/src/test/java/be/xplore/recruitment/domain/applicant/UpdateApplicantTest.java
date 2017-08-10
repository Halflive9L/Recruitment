package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/28/2017
 */
public class UpdateApplicantTest {
    private UpdateApplicant useCase;
    private List<Applicant> mockApplicants;


    @Before
    public void initUseCase() {
        MockApplicantRepo repository = new MockApplicantRepo();
        mockApplicants = repository.mockApplicants;
        useCase = new UpdateApplicantUseCase(repository);
    }

    @Test
    public void testUpdateApplicant() {
        useCase.updateApplicant(getRequestFromApplicant(getValidApplicant()), applicant -> {});
        assertEquals(getExpectedApplicant(), mockApplicants.get(0));
    }

    @Test(expected = InvalidPhoneException.class)
    public void testUpdateApplicantWithInvalidPhone() {
        useCase.updateApplicant(getRequestFromApplicant(getApplicantWithInvalidPhone()), applicant -> {});
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNonExistingApplicant() {
        useCase.updateApplicant(getRequestFromApplicant(Applicant.builder()
                .withApplicantId(500)
                .build()), applicant -> {});
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
                .withApplicantId(1)
                .withAddress("Mortsel")
                .withPhone("+32452148963").build();
    }

    @Ignore
    private Applicant getApplicantWithInvalidPhone() {
        return Applicant.builder()
                .withApplicantId(1)
                .withPhone("a")
                .build();
    }

    @Ignore
    private Applicant getExpectedApplicant() {
        return Applicant.builder()
                .withFirstName("John")
                .withLastName("Smith")
                .withApplicantId(1)
                .withDateOfBirth(LocalDate.of(1996, 10, 3))
                .withAddress("Mortsel")
                .withEducation("College")
                .withEmail("john.smith@example.com")
                .withPhone("+32452148963").build();
    }
}
