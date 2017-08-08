package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.CreateApplicant;
import be.xplore.recruitment.domain.applicant.CreateApplicantRequest;
import be.xplore.recruitment.domain.applicant.DeleteApplicant;
import be.xplore.recruitment.domain.applicant.ReadApplicant;
import be.xplore.recruitment.domain.applicant.ReadApplicantRequest;
import be.xplore.recruitment.domain.applicant.UpdateApplicant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicantControllerTest {
    @Mock
    private CreateApplicant createApplicant;

    @Mock
    private ReadApplicant readApplicant;

    @Mock
    private UpdateApplicant updateApplicant;

    @Mock
    private DeleteApplicant deleteApplicant;

    @InjectMocks
    private ApplicantController controller = new ApplicantController();

    @Test
    public void testCreateApplicant() {
        ArgumentCaptor<CreateApplicantRequest> captor = ArgumentCaptor.forClass(CreateApplicantRequest.class);
        controller.addApplicant(new JsonApplicant());
        verify(createApplicant).createApplicant(captor.capture(), any());
    }

    @Test
    public void testReadApplicantById() {
        ArgumentCaptor<ReadApplicantRequest> captor = ArgumentCaptor.forClass(ReadApplicantRequest.class);
        controller.getApplicantById(1);
        verify(readApplicant).readApplicantById(captor.capture(), any());
    }

    @Test
    public void testReadAllApplicants() {
        controller.getApplicantByParam(new JsonApplicant());
        verify(readApplicant).readAllApplicants(any());
    }

    @Test
    public void testReadApplicantsByParam() {
        ArgumentCaptor<ReadApplicantRequest> captor = ArgumentCaptor.forClass(ReadApplicantRequest.class);
        JsonApplicant applicant = new JsonApplicant();
        applicant.setFirstName("Danny");
        controller.getApplicantByParam(applicant);
        verify(readApplicant).readApplicantsByParam(captor.capture(), any());
    }
}