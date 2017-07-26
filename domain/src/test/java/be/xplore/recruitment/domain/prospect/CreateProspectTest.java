package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateProspectTest {

    @Mock
    private ProspectRepository repository;

    private CreateProspect useCase;

    @Before
    public void initUseCase(){
        useCase = new CreateProspectUseCase(repository);
    }

    @Test
    public void testCreateProspect() {
        useCase.createProspect(new Prospect.ProspectBuilder("John", "Smith").createProspect());
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateProspectWithInvalidEmail() {
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setEmail("a");
        useCase.createProspect(builder.createProspect());
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateProspectWithInvalidPhone() {
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setPhone("a");
        useCase.createProspect(builder.createProspect());
    }

    @Test
    public void testCreateProspectWithValidEmail() {
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setEmail("test.name@example.com");
        useCase.createProspect(builder.createProspect());
    }

    @Test
    public void testCreateProspectWithValidPhone() {
        Prospect.ProspectBuilder builder = new Prospect.ProspectBuilder("John", "Smith");
        builder.setPhone("+32425786314");
        useCase.createProspect(builder.createProspect());
    }
}
