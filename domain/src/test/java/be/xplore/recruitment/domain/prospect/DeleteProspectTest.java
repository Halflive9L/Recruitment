package be.xplore.recruitment.domain.prospect;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteProspectTest {

    private MockProspectRepo repository = new MockProspectRepo();

    @Test
    public void testDeleteProspect() {
        repository.deleteProspect(1);
        Assert.assertEquals(repository.mockProspects[1], null);
    }
}
