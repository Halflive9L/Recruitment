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
    public void initUseCase(){
        useCase = new CreateApplicantUseCase(repository);
    }

    @Test
    public void testCreateApplicant() {
        useCase.createApplicant(new Applicant.ApplicantBuilder("John", "Smith").createApplicant());
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateApplicantWithInvalidPhone() {
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setPhone("a");
        useCase.createApplicant(builder.createApplicant());
    }

    @Test
    public void testCreateApplicantWithValidPhone() {
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setPhone("+32425786314");
        useCase.createApplicant(builder.createApplicant());
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateApplicantWithInvalidEmail() {
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setEmail("a");
        useCase.createApplicant(builder.createApplicant());
    }

    @Test
    public void testCreateApplicantWithValidEmail() {
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setEmail("test.name@example.com");
        useCase.createApplicant(builder.createApplicant());
    }

    @Test(expected = InvalidDateException.class)
    public void testCreateApplicantWithInvalidDate() {
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setDateOfBirth(new Calendar.Builder().setDate(1899, 12, 31).build().getTime());
        useCase.createApplicant(builder.createApplicant());
    }

    @Test
    public void testCreateApplicantWithValidDate() {
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setDateOfBirth(new Calendar.Builder().setDate(1993, 4, 4).build().getTime());
        useCase.createApplicant(builder.createApplicant());
    }
}