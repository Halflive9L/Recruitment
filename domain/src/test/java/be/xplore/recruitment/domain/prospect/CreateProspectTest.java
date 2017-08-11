package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public class CreateProspectTest {
    private CreateProspect useCase;
    private List<Prospect> mockRepo;

    @Before
    public void initUseCase() {
        MockProspectRepo repo = new MockProspectRepo();
        mockRepo = repo.mockProspects;
        useCase = new CreateProspectUseCase(repo);
    }

    @Test
    public void testCreateProspect() {
        Prospect expected = Prospect.builder().withFirstName("stijn").build();
        useCase.createProspect(getRequestFromProspect(expected), prospectId -> {
        });
        assertEquals(3, mockRepo.size());
        assertEquals(expected, mockRepo.get(2));
    }

    @Test(expected = InvalidPhoneException.class)
    public void testCreateProspectWithInvalidPhone() {
        CreateProspectRequest request = getRequestFromProspect(Prospect.builder()
                .withPhone("a").build());
        useCase.createProspect(request, prospectId -> {
        });
    }

    @Test
    public void testCreateProspectWithValidPhone() {
        CreateProspectRequest request = getRequestFromProspect(Prospect.builder()
                .withPhone("+32424589632").build());
        useCase.createProspect(request, id -> {
        });
    }

    @Test(expected = InvalidEmailException.class)
    public void testCreateProspectWithInvalidEmail() {
        CreateProspectRequest request = getRequestFromProspect(Prospect.builder()
                .withEmail("a").build());
        useCase.createProspect(request, prospect -> {

        });
    }

    @Test
    public void testCreateProspectWithValidEmail() {
        CreateProspectRequest request = getRequestFromProspect(Prospect.builder()
                .withEmail("test.name@example.com").build());
        useCase.createProspect(request, id -> {
        });
    }

    @SuppressWarnings("Duplicates")
    @Ignore
    private CreateProspectRequest getRequestFromProspect(Prospect prospect) {
        return CreateProspectRequestBuilder.aCreateProspectRequest()
                .withFirstName(prospect.getFirstName())
                .withLastName(prospect.getLastName())
                .withEmail(prospect.getEmail())
                .withPhone(prospect.getPhone())
                .build();
    }
}
