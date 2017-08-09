package be.xplore.recruitment.domain.prospect;


import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
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
public class DeleteProspectTest {
    private DeleteProspect useCase;
    private List<Prospect> mockProspects;

    @Before
    public void initUseCase() {
        MockProspectRepo repository = new MockProspectRepo();
        mockProspects = repository.mockProspects;
        useCase = new DeleteProspectUseCase(repository);
    }

    @Test
    public void testDeleteProspectTest() {
        useCase.deleteProspect(new DeleteProspectRequest(1), prospectId -> {
        });
        assertEquals(1, mockProspects.size());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNonExistingProspectTest() {
        useCase.deleteProspect(new DeleteProspectRequest(500), prospectId -> {
        });
    }
}
