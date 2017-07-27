package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateProspectTest {
    private Prospect[] prospects = {
            Prospect.builder("john", "smith")
                    .withId(1)
                    .withEmail("john.smith@example.com")
                    .withPhone("+32424963258").build(),
            Prospect.builder("leeroy", "jenkins")
                    .withId(2)
                    .withEmail("leeroy@jenkins.com")
                    .withPhone("+32 420 00 1337").build()
    };
    private final ProspectRepository repository = new ProspectRepository() {

        @Override
        public void createProspect(Prospect prospect) {
        }

        @Override
        public List<Prospect> findAll() {
            return asList(prospects);
        }

        @Override
        public Prospect findProspectById(long id) {
            for (Prospect p : prospects) {
                if (p.getProspectId() == id) {
                    return p;
                }
            }
            return null;
        }

        @Override
        public void deleteProspect(long id) {
        }

        @Override
        public Prospect updateProspect() {
            return null;
        }
    };

    private UpdateProspect useCase;

    @Before
    public void initUseCase() {
        useCase = new UpdateProspectUseCase(repository);
    }

    @Test
    public void testUpdateProspect() {
        UpdateProspectRequest request = new UpdateProspectRequest();
        request.prospectId = 1;
        request.firstName = "Smith";
        request.lastName = "John";
        useCase.updateProspect(request, prospectId -> {});
        System.out.println(repository.findProspectById(1));
    }

    /*@Test(expected = InvalidEmailException.class)
    public void testCreateProspectWithInvalidEmail() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.lastName = "Smith";
        request.email = "a";
        useCase.createProspect(request, prospectId -> {
        });
    }

    @Test
    public void testCreateProspectWithValidEmail() {
        CreateProspectRequest request = new CreateProspectRequest();
       // request.prospect = new Prospect.ProspectBuilder("John", "Smith")
         //       .withEmail("test@example.com").build();
        request.firstName = "John";
        request.lastName = "Smith";
        request.email = "test@example.com";
        useCase.createProspect(request, prospectId -> {
        });
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateProspectWithInvalidPhone() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.lastName = "Smith";
        request.phone = "a";
        useCase.createProspect(request, prospectId -> {
        });
    }

    @Test
    public void testCreateProspectWithValidPhone() {
        CreateProspectRequest request = new CreateProspectRequest();
        request.firstName = "John";
        request.firstName = "Smith";
        request.phone = "+3248657569";
        useCase.createProspect(request, prospectId -> {
        });
    }*/
}
