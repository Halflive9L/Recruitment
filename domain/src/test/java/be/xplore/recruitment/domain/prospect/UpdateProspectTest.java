package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.InvalidPhoneException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateProspectTest {
    private UpdateProspect useCase;
    private List<Prospect> mockProspects;


    @Before
    public void initUseCase() {
        MockProspectRepo repository = new MockProspectRepo();
        mockProspects = repository.mockProspects;
        useCase = new UpdateProspectUseCase(repository);
    }

    @Test
    public void testUpdateProspect() {
        useCase.updateProspect(getRequestFromProspect(getValidProspect()), prospect -> {
        });
        assertEquals(getExpectedProspect(), mockProspects.get(0));
    }

    @Test(expected = InvalidPhoneException.class)
    public void testUpdateProspectWithInvalidPhone() {
        useCase.updateProspect(getRequestFromProspect(getProspectWithInvalidPhone()), prospect -> {
        });
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNonExistingProspect() {
        useCase.updateProspect(getRequestFromProspect(Prospect.builder().withId(500).build()), prospect -> {
        });
    }

    @Ignore
    private UpdateProspectRequest getRequestFromProspect(Prospect prospect) {
        UpdateProspectRequest request = new UpdateProspectRequest(prospect.getProspectId());
        request.firstName = prospect.getFirstName();
        request.lastName = prospect.getLastName();
        request.email = prospect.getEmail();
        request.phone = prospect.getPhone();
        return request;
    }

    @Ignore
    private Prospect getValidProspect() {
        return Prospect.builder()
                .withId(1)
                .withPhone("+32452148963").build();
    }

    @Ignore
    private Prospect getProspectWithInvalidPhone() {
        return Prospect.builder()
                .withId(1)
                .withPhone("a")
                .build();
    }

    @Ignore
    private Prospect getExpectedProspect() {
        return Prospect.builder()
                .withId(1)
                .withFirstName("John")
                .withLastName("Smith")
                .withEmail("john.smith@example.com")
                .withPhone("+32452148963").build();
    }
}
