package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidDateException;
import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Before;
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
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.applicant = new Applicant.ApplicantBuilder("John", "Smith").createApplicant();
        useCase.createApplicant(request, applicantId -> {
        });
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateApplicantWithInvalidPhone() {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.applicant = new Applicant.ApplicantBuilder("John", "Smith")
                .setPhone("a").createApplicant();
        useCase.createApplicant(request, applicantId -> {
        });
    }

    @Test
    public void testCreateApplicantWithValidPhone() {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.applicant = new Applicant.ApplicantBuilder("John", "Smith")
                .setPhone("+32424589632").createApplicant();
        useCase.createApplicant(request, id -> {
        });
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateApplicantWithInvalidEmail() {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.applicant = new Applicant.ApplicantBuilder("John", "Smith")
                .setEmail("a").createApplicant();
        useCase.createApplicant(request, id -> {
        });
    }

    @Test
    public void testCreateApplicantWithValidEmail() {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.applicant = new Applicant.ApplicantBuilder("John", "Smith")
                .setEmail("test.name@example.com").createApplicant();
        useCase.createApplicant(request, id -> {
        });
    }

    @Test(expected = InvalidDateException.class)
    public void testCreateApplicantWithInvalidDate() {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.applicant = new Applicant.ApplicantBuilder("John", "Smith")
                .setDateOfBirth(new Calendar.Builder().setDate(1899, 12, 31).build().getTime())
                .createApplicant();
        useCase.createApplicant(request, id -> {
        });
    }

    @Test
    public void testCreateApplicantWithValidDate() {
        CreateApplicantRequest request = new CreateApplicantRequest();
        request.applicant = new Applicant.ApplicantBuilder("John", "Smith")
                .setDateOfBirth(new Calendar.Builder().setDate(1993, 4, 4).build().getTime())
                .createApplicant();
        useCase.createApplicant(request, id -> {
        });
    }
}