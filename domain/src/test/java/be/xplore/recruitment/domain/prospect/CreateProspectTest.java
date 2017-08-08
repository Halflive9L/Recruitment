package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateProspectTest {

    @Mock
    private MockProspectRepo repository;

    private CreateProspect useCase;

    @Before
    public void initUseCase() {
        repository = new MockProspectRepo();
        useCase = new CreateProspectUseCase(repository);
    }

    @Test
    public void testCreateProspect() {
        Prospect expected = Prospect.builder().withFirstName("stijn").build();
        CreateProspectRequest request = getRequestFromProspect(expected);
        useCase.createProspect(request, prospectId -> {
        });
        assertEquals(3, repository.mockProspects.size());
        assertEquals(expected, repository.mockProspects.get(2));
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateProspectWithInvalidEmail() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.lastName = "Smith";
        request.email = "a";
        useCase.createProspect(request, prospects -> {
        });
        assertEquals(repository.mockProspects.size(), 2);
        assertEquals(repository.mockProspects.get(2),
                Prospect.builder().withFirstName(request.firstName).withLastName(request.lastName).build());
    }

    @Test
    public void testCreateProspectWithValidEmail() {
        CreateProspectRequest request = getRequestFromProspect(Prospect.builder()
                .withEmail("test.name@example.com").build());
        useCase.createProspect(request, id -> {
        });
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateProspectWithInvalidPhone() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.lastName = "Smith";
        request.phone = "a";
        useCase.createProspect(request, prospects -> {
        });
        assertEquals(2, repository.mockProspects.size());
        assertEquals(repository.mockProspects.get(2),
                Prospect.builder().withFirstName(request.firstName).withLastName(request.lastName).build());
    }

    @Test
    public void testCreateProspectWithValidPhone() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.lastName = "Smith";
        request.phone = "+3248657569";
        repository.createProspect(Prospect.builder().withFirstName(request.firstName).withLastName(request.lastName)
                .withPhone(request.phone).build());
        assertEquals(3, repository.mockProspects.size());
        assertEquals(repository.mockProspects.get(2),
                Prospect.builder().withFirstName(request.firstName).withLastName(request.lastName)
                        .withPhone(request.phone).build());
    }

    @Ignore
    private CreateProspectRequest getRequestFromProspect(Prospect prospect) {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = prospect.getFirstName();
        request.lastName = prospect.getLastName();
        request.email = prospect.getEmail();
        request.phone = prospect.getPhone();
        return request;
    }
}
