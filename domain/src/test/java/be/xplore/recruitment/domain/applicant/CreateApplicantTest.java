package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public class CreateApplicantTest {
    private CreateApplicant useCase;
    private List<Applicant> mockRepo;

    @Before
    public void initUseCase() {
        MockApplicantRepo repo = new MockApplicantRepo();
        mockRepo = repo.mockApplicants;
        useCase = new CreateApplicantUseCase(repo);
    }

    @Test
    public void testCreateApplicant() {
        Applicant expected = Applicant.builder().withFirstName("stijn").build();
        useCase.createApplicant(getRequestFromApplicant(expected), applicantId -> {
        });
        assertEquals(3, mockRepo.size());
        assertEquals(expected, mockRepo.get(2));
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateApplicantWithInvalidPhone() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder()
                .withPhone("a").build());
        useCase.createApplicant(request, applicantId -> {
        });
    }

    @Test
    public void testCreateApplicantWithValidPhone() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder()
                .withPhone("+32424589632").build());
        useCase.createApplicant(request, id -> {
        });
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateApplicantWithInvalidEmail() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder()
                .withEmail("a").build());
        useCase.createApplicant(request, applicant -> {

        });
    }

    @Test
    public void testCreateApplicantWithValidEmail() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder()
                .withEmail("test.name@example.com").build());
        useCase.createApplicant(request, id -> {
        });
    }

    @Test(expected = InvalidDateException.class)
    public void testCreateApplicantWithInvalidDate() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder()
                .withDateOfBirth(LocalDate.of(1899, 12, 31))
                .build());
        useCase.createApplicant(request, id -> {
        });
    }

    @Test
    public void testCreateApplicantWithValidDate() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder()
                .withDateOfBirth(LocalDate.of(1993, 4, 4))
                .build());
        useCase.createApplicant(request, id -> {
        });
    }

    @Ignore
    private CreateApplicantRequest getRequestFromApplicant(Applicant applicant) {
        return CreateApplicantRequestBuilder.aCreateApplicantRequest()
                .withAddress(applicant.getAddress())
                .withDateOfBirth(applicant.getDateOfBirth())
                .withEducation(applicant.getEducation())
                .withFirstName(applicant.getFirstName())
                .withLastName(applicant.getLastName())
                .withEmail(applicant.getEmail())
                .withPhone(applicant.getPhone())
                .build();
    }
}