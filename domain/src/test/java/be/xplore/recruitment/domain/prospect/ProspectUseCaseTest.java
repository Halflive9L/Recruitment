package be.xplore.recruitment.domain.prospect;


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
public class ProspectUseCaseTest {

    @Mock
    private ProspectRepository repository;

    @Test
    public void testCreateProspect() {
        CreateProspectUseCase useCase = new CreateProspectUseCase(repository);
        useCase.createProspect(new Prospect.ProspectBuilder("John", "Smith").createProspect());
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateProspectWithInvalidEmail(){
        CreateProspectUseCase useCase = new CreateProspectUseCase(repository);
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setEmail("a");
        useCase.createProspect(builder.createProspect());
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateProspectWithInvalidPhone(){
        CreateProspectUseCase useCase = new CreateProspectUseCase(repository);
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setPhone("a");
        useCase.createProspect(builder.createProspect());
    }

    @Test
    public void testCreateProspectWithValidEmail(){
        CreateProspectUseCase useCase = new CreateProspectUseCase(repository);
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setEmail("test.name@example.com");
        useCase.createProspect(builder.createProspect());
    }

    @Test
    public void testCreateProspectWithValidPhone(){
        CreateProspectUseCase useCase = new CreateProspectUseCase(repository);
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setPhone("+32425786314");
        useCase.createProspect(builder.createProspect());
    }
}
