package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
        assertArrayEquals(mockApplicants, applicantResponse[0]);
    }

    @Test
    public void testReadApplicantById() {
        ReadApplicantRequest request = getRequestFromApplicant(Applicant.builder().withId(1).build());
        final Applicant[] responseApplicant = new Applicant[1];
        useCase.readApplicantById(request, applicant -> responseApplicant[0] = applicant);
        assertEquals(responseApplicant[0], mockApplicants[0]);
    }

    @Test(expected = NotFoundException.class)
    public void testReadApplicantById_IdDoesNotExist() {
        ReadApplicantRequest request = getRequestFromApplicant(Applicant.builder().withId(500).build());
        useCase.readApplicantById(request, applicant -> {
        });
    }

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
