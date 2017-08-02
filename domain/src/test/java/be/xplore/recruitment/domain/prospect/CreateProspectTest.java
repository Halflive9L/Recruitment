package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
    private MockProspectRepo repository;

    private CreateProspect useCase;

    @Before
    public void initUseCase() {
        useCase = new CreateProspectUseCase(repository);
        repository = new MockProspectRepo();
    }

    @Test
    public void testCreateProspect() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.lastName = "Smith";
        repository.createProspect(Prospect.builder().withFirstName(request.firstName).withLastName(request.lastName)
                .build());
        useCase.createProspect(request, prospects -> {
            for (ProspectResponseModel p: prospects
                 ) {
                Assert.assertEquals(request.firstName, p.getFirstName());
                Assert.assertEquals(request.lastName, p.getLastName());
            }
        });
        Assert.assertEquals(repository.mockProspects.size(), 3);
        Assert.assertEquals(repository.mockProspects.get(2),
               Prospect.builder().withFirstName(request.firstName).withLastName(request.lastName).build());
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateProspectWithInvalidEmail() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.lastName = "Smith";
        request.email = "a";
        useCase.createProspect(request, prospects -> {
            for (ProspectResponseModel p : prospects
                 ) {
                Assert.assertEquals(request.firstName, p.getFirstName());
                Assert.assertEquals(request.lastName, p.getLastName());
                Assert.assertNotEquals(request.email, p.getEmail());
            }

        });
        Assert.assertEquals(repository.mockProspects.size(), 2);
        Assert.assertEquals(repository.mockProspects.get(2),
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
            for (ProspectResponseModel p : prospects
                    ) {
                Assert.assertEquals(request.firstName, p.getFirstName());
                Assert.assertEquals(request.lastName, p.getLastName());
                Assert.assertNotEquals(request.phone, p.getPhone());
            }
        });
        Assert.assertEquals(2, repository.mockProspects.size());
        Assert.assertEquals(repository.mockProspects.get(2),
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
        useCase.createProspect(request, prospects -> {
            for (ProspectResponseModel p : prospects
                    ) {
                Assert.assertEquals(request.firstName, p.getFirstName());
                Assert.assertEquals(request.lastName, p.getLastName());
                Assert.assertEquals(request.phone, p.getPhone());
            }
        });
        Assert.assertEquals(repository.mockProspects.size(), 3);
        Assert.assertEquals(repository.mockProspects.get(2),
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
