package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.CreateProspect;
import be.xplore.recruitment.domain.prospect.CreateProspectRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
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
    public void testCreateProspect() {
        ArgumentCaptor<CreateProspectRequest> captor = ArgumentCaptor.forClass(CreateProspectRequest.class);
        controller.addProspect(new JsonProspect());
        verify(createProspect).createProspect(captor.capture(), any());
    }
}