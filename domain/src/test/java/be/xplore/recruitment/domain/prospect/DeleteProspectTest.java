package be.xplore.recruitment.domain.prospect;


import org.junit.Assert;
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
public class DeleteProspectTest {

    @Mock
    private MockProspectRepo repository;

    private DeleteProspect useCase;

    @Before
    public void initUseCase() {
        useCase = new DeleteProspectUseCase(repository);
        repository = new MockProspectRepo();
    }

    @Test
    public void testDeleteProspect() {
        DeleteProspectRequest request = new DeleteProspectRequest(1);
        repository.deleteProspect(1);
        Assert.assertEquals(repository.mockProspects.size(), 1);
    }
}
