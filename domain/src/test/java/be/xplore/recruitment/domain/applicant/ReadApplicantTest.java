package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadApplicantTest {
    private ReadApplicant useCase;

    private Applicant[] mockApplicants;

    @Before
    public void initUseCase() {
        MockApplicantRepo repository = new MockApplicantRepo();
        mockApplicants = repository.mockApplicants;
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
