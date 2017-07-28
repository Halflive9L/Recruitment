package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateApplicantTest {
    @Mock
    private ApplicantRepository repository;

    private CreateApplicant useCase;

    @Before
    public void initUseCase() {
        useCase = new CreateApplicantUseCase(repository);
    }

    @Test
    public void testCreateApplicant() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder().build());
        useCase.createApplicant(request, applicantId -> {
        });
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
        useCase.createApplicant(request, id -> {
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
                .withDateOfBirth(new Calendar.Builder().setDate(1899, 12, 31).build().getTime())
                .build());
        useCase.createApplicant(request, id -> {
        });
    }

    @Test
    public void testCreateApplicantWithValidDate() {
        CreateApplicantRequest request = getRequestFromApplicant(Applicant.builder()
                .withDateOfBirth(new Calendar.Builder().setDate(1993, 4, 4).build().getTime())
                .build());
        useCase.createApplicant(request, id -> {
        });
    }

    @Ignore
    private CreateApplicantRequest getRequestFromApplicant(Applicant applicant) {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        return request;
    }
}