package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateApplicantUseCaseTest {
    @Mock
    private ApplicantRepository repository;

    @Test
    public void testCreateApplicant() {
        CreateApplicantUseCase useCase = new CreateApplicantUseCase(repository);
        useCase.createApplicant(new Applicant.ApplicantBuilder("John", "Smith").createApplicant());
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateApplicantWithInvalidEmail() {
        CreateApplicantUseCase useCase = new CreateApplicantUseCase(repository);
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setEmail("a");
        useCase.createApplicant(builder.createApplicant());
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateApplicantWithInvalidPhone() {
        CreateApplicantUseCase useCase = new CreateApplicantUseCase(repository);
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setPhone("a");
        useCase.createApplicant(builder.createApplicant());
    }

    @Test
    public void testCreateApplicantWithValidEmail() {
        CreateApplicantUseCase useCase = new CreateApplicantUseCase(repository);
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setEmail("test.name@example.com");
        useCase.createApplicant(builder.createApplicant());
    }

    @Test
    public void testCreateApplicantWithValidPhone() {
        CreateApplicantUseCase useCase = new CreateApplicantUseCase(repository);
        Applicant.ApplicantBuilder builder = new Applicant.ApplicantBuilder("John", "Smith");
        builder.setPhone("+32425786314");
        useCase.createApplicant(builder.createApplicant());
    }

}
