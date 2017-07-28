package be.xplore.recruitment.web.applicant;

import be.xplore.recruitment.domain.applicant.CreateApplicant;
import be.xplore.recruitment.domain.applicant.CreateApplicantRequest;
import be.xplore.recruitment.domain.applicant.DeleteApplicant;
import be.xplore.recruitment.domain.applicant.ReadAllApplicantsResponse;
import be.xplore.recruitment.domain.applicant.ReadApplicant;
import be.xplore.recruitment.domain.applicant.ReadApplicantRequest;
import be.xplore.recruitment.domain.applicant.ReadApplicantsByParamResponse;
import be.xplore.recruitment.domain.applicant.UpdateApplicant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
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
        assertEquals(controller.addApplicant(new JsonApplicant()).getStatusCodeValue(), HttpStatus.CREATED.value());
        verify(createApplicant).createApplicant(captor.capture(), isA(Consumer.class));
    }

    @Test
    public void testReadApplicantById(){
        ArgumentCaptor<ReadApplicantRequest> captor = ArgumentCaptor.forClass(ReadApplicantRequest.class);
        assertEquals(controller.getApplicantById(1).getStatusCodeValue(), HttpStatus.OK.value());
        verify(readApplicant).readApplicantById(captor.capture(), isA(Consumer.class));
    }

    @Test
    public void testReadAllApplicants(){
        assertEquals(controller.getApplicantByParam(new JsonApplicant()).getStatusCodeValue(), HttpStatus.OK.value());
        verify(readApplicant).readAllApplicants(isA(ReadAllApplicantsResponse.class));
    }

    @Test
    public void testReadApplicantsByParam(){
        ArgumentCaptor<ReadApplicantRequest> captor = ArgumentCaptor.forClass(ReadApplicantRequest.class);
        JsonApplicant applicant = new JsonApplicant();
        applicant.setFirstName("Danny");
        assertEquals(controller.getApplicantByParam(applicant).getStatusCodeValue(), HttpStatus.OK.value());
        verify(readApplicant).readApplicantsByParam(captor.capture(), isA(ReadApplicantsByParamResponse.class));
    }
}