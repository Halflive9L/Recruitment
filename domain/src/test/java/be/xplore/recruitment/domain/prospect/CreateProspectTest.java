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
    public void initUseCase() {
        useCase = new CreateProspectUseCase(repository);
    }

    @Test
    public void testCreateProspect() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.prospect = new Prospect.ProspectBuilder("John", "Smith").createProspect();
        useCase.createProspect(request, prospectId -> {});
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateProspectWithInvalidEmail() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.prospect = new Prospect.ProspectBuilder("John", "Smith")
                .withEmail("a").createProspect();
        useCase.createProspect(request, prospectId -> {});
    }

    @Test
    public void testCreateProspectWithValidEmail() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.prospect = new Prospect.ProspectBuilder("John", "Smith")
                .withEmail("test@example.com").createProspect();
        useCase.createProspect(request, prospectId -> {});
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateProspectWithInvalidPhone() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.prospect = new Prospect.ProspectBuilder("John", "Smith")
                .withPhone("a").createProspect();
        useCase.createProspect(request, prospectId -> {});
    }

    @Test
    public void testCreateProspectWithValidPhone() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.prospect = new Prospect.ProspectBuilder("John", "Smith")
                .withPhone("+3248657569").createProspect();
        useCase.createProspect(request, prospectId -> {});
    }
}
