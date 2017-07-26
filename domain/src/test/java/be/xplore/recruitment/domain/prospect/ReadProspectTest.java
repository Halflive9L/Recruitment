package be.xplore.recruitment.domain.prospect;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadProspectTest {
    private Prospect[] prospects = {
            new Prospect(1, new Prospect.ProspectBuilder("john", "smith")
                    .setEmail("john.smith@example.com")
                    .setPhone("+32424963258").createProspect()),
            new Prospect(2, new Prospect.ProspectBuilder("leeroy", "jenkins")
                    .setEmail("leeroy@jenkins.com")
                    .setPhone("+32 420 00 1337").createProspect())
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
    };

    private ReadProspect useCase;

    @Before
    public void initUseCase() {
        useCase = new ReadProspectUseCase(repository);
    }

    @Test
    public void testReadAllProspects() {
        List<Prospect> allProspects = useCase.readAllProspects();
        allProspects.forEach(System.out::println);
        assertThat(allProspects, CoreMatchers.equalTo(asList(prospects)));
    }

    @Test
    public void testReadProspectById() {
        Prospect result = useCase.readProspectById(1);
        Prospect expected = prospects[0];
        assertEquals(result.getFirstName(), expected.getFirstName());
        assertEquals(result.getLastName(), expected.getLastName());
        assertEquals(result.getEmail(), expected.getEmail());
        assertEquals(result.getPhone(), expected.getPhone());
    }

    @Test
    public void testReadProspectById_IdDoesNotExist(){
        Prospect result = useCase.readProspectById(100);
        assertNull(result);
    }
}
