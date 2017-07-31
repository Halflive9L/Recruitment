package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadProspectTest {


    private MockProspectRepo repository = new MockProspectRepo();


    @Test
    public void testReadAllProspects() {
        List<Prospect> prospects = repository.findAll();
        assertEquals(prospects,repository.mockProspects);
    }

    @Test
    public void testReadProspectById() {
        Prospect prospect = repository.findProspectById(1);
        assertEquals(prospect, repository.mockProspects.get(0));
    }

    @Test(expected = NotFoundException.class)
    public void testReadProspectById_IdDoesNotExist() {
        Prospect prospect = repository.findProspectById(45465);
    }
}
