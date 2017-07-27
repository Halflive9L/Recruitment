package be.xplore.recruitment.domain.applicant;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadApplicantTest {
    private Applicant[] applicants = {
            new Applicant(1, new Applicant.ApplicantBuilder("john", "smith")
                    .setDateOfBirth(new Calendar.Builder().setDate(1996, 10, 3).build().getTime())
                    .setAddress("Antwerp")
                    .setEducation("College")
                    .setEmail("john.smith@example.com")
                    .setPhone("+32424963258").build()),
            new Applicant(2, new Applicant.ApplicantBuilder("leeroy", "jenkins")
                    .setDateOfBirth(new Calendar.Builder().setDate(1986, 3, 10).build().getTime())
                    .setAddress("Kontich")
                    .setEducation("University")
                    .setEmail("leeroy@jenkins.com")
                    .setPhone("+32 420 00 1337").build())
    };
    private final ApplicantRepository repository = new ApplicantRepository() {

        @Override
        public void createApplicant(Applicant applicant) {
        }

        @Override
        public List<Applicant> findAll() {
            return asList(applicants);
        }

        @Override
        public Applicant findApplicantById(long id) {
            for (Applicant p : applicants) {
                if (p.getApplicantId() == id) {
                    return p;
                }
            }
            return null;
        }
    };

    private ReadApplicant useCase;

    @Before
    public void initUseCase() {
        useCase = new ReadApplicantUseCase(repository);
    }

    @Test
    public void testReadAllApplicants() {
        List<Applicant> allApplicants = useCase.readAllApplicants();
        allApplicants.forEach(System.out::println);
        assertThat(allApplicants, CoreMatchers.equalTo(asList(applicants)));
    }

    @Test
    public void testReadApplicantById() {
        Applicant result = useCase.readApplicantById(1);
        Applicant expected = applicants[0];
        assertEquals(result.getFirstName(), expected.getFirstName());
        assertEquals(result.getLastName(), expected.getLastName());
        assertEquals(result.getAddress(), expected.getAddress());
        assertEquals(result.getEmail(), expected.getEmail());
        assertEquals(result.getPhone(), expected.getPhone());
        assertEquals(result.getDateOfBirth(), expected.getDateOfBirth());
        assertEquals(result.getEducation(), expected.getEducation());
    }

    @Test
    public void testReadApplicantById_IdDoesNotExist() {
        Applicant result = useCase.readApplicantById(100);
        assertNull(result);
    }
}
