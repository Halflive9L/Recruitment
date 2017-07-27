package be.xplore.recruitment.domain.applicant;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadApplicantTest {
    private Applicant[] mockApplicants = {
            Applicant.builder()
                    .withFirstName("John").withLastName("Smith").withId(1)
                    .withDateOfBirth(new Calendar.Builder().setDate(1996, 10, 3).build()
                            .getTime())
                    .withAddress("Antwerp")
                    .withEducation("College")
                    .withEmail("john.smith@example.com")
                    .withPhone("+32424963258").build(),
            Applicant.builder()
                    .withFirstName("Leeroy").withLastName("Jenkins").withId(2)
                    .withDateOfBirth(new Calendar.Builder().setDate(1986, 3, 10).build()
                            .getTime())
                    .withAddress("Kontich")
                    .withEducation("University")
                    .withEmail("leeroy@jenkins.com")
                    .withPhone("+32 420 00 1337").build()
    };
    private final ApplicantRepository repository = new ApplicantRepository() {

        @Override
        public void createApplicant(Applicant applicant) {
        }

        @Override
        public List<Applicant> findAll() {
            return asList(mockApplicants);
        }

        @Override
        public Applicant findApplicantById(long id) {
            for (Applicant p : mockApplicants) {
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
        final Applicant[][] applicantResponse = new Applicant[1][2];
        useCase.readAllApplicants(applicants -> applicantResponse[0] = (Applicant[]) applicants.toArray());
        for (Applicant applicant : applicantResponse[0]) {
            System.out.println(applicant);
        }
        assertArrayEquals(mockApplicants, applicantResponse[0]);
    }
/*
    @Test
    public void testReadApplicantById() {
        Applicant result = useCase.readApplicantById(1);
        Applicant expected = mockApplicants[0];
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
        Applicant result = useCase.readApplicantById();
        assertNull(result);
    }*/

    @Ignore
    private ReadApplicantRequest getRequestFromApplicant(Applicant applicant) {
        ReadApplicantRequest request = new ReadApplicantRequest();
        request.applicantId = applicant.getApplicantId();
        request.address = applicant.getAddress();
        request.dateOfBirth = applicant.getDateOfBirth();
        request.education = applicant.getEducation();
        request.firstName = applicant.getFirstName();
        request.lastName = applicant.getLastName();
        request.email = applicant.getEmail();
        request.phone = applicant.getPhone();
        return request;
    }
}
