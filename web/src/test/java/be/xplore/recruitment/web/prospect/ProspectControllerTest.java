package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.CreateProspect;
import be.xplore.recruitment.domain.prospect.CreateProspectRequest;
import be.xplore.recruitment.domain.prospect.CreateProspectResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

/**
 * @author Stijn Schack
 * @since 7/27/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ProspectControllerTest {
    @Mock
    private CreateProspect createProspect;

    @InjectMocks
    private ProspectController controller = new ProspectController();

    @Test
    public void addProspectTest() {
        ArgumentCaptor<CreateProspectRequest> requestArgumentCaptor = ArgumentCaptor.forClass(CreateProspectRequest.class);
        assertThat(controller.addProspect(new JsonProspect()).getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        verify(createProspect).createProspect(requestArgumentCaptor.capture(), isA(CreateProspectResponse.class));
        System.out.println(requestArgumentCaptor.getValue());
    }
}