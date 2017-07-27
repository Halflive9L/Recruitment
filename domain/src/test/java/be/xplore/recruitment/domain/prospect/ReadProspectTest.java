package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.InvalidEmailException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

    private ReadProspect useCase;

    @Before
    public void initUseCase() {
        useCase = new ReadProspectUseCase(repository);
    }


    @Test
    public void testReadAllProspects() {
            final Prospect[][] prospectResponse = new Prospect[1][2];
            useCase.readAllProspects(prospects -> prospectResponse[0] = (Prospect[]) prospects.toArray());
        for (int i = 0; i < 2; i++) {
            System.out.println(prospectResponse[0][i]);
        }
        assertArrayEquals(prospects, prospectResponse[0]);
    }

    @Test
    public void testReadProspectById() {
        ReadProspectRequest request = new ReadProspectRequest();
        request.prospectId = 1;
        useCase.readProspectById(request, Prospect -> {});
    }

    @Test(expected = NotFoundException.class)
    public void testReadProspectById_IdDoesNotExist() {
        ReadProspectRequest request = new ReadProspectRequest();
        request.prospectId = 63544;
        useCase.readProspectById(request, Prospect -> {});
    }
}
