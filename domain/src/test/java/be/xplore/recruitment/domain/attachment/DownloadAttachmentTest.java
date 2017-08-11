package be.xplore.recruitment.domain.attachment;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class DownloadAttachmentTest {
    private DownloadAttachment useCase;

    @Before
    public void initUseCase() {
        useCase = new DownloadAttachmentUseCase(new MockAttachmentRepo());
    }

    @Test
    public void testDownloadAttachment() {
        DownloadAttachmentRequest request = new DownloadAttachmentRequest(1,
                Mockito.mock(OutputStream.class));
        useCase.downloadAttachment(request, responseModel -> {
            checkResponse(responseModel);
        });
    }

    private void checkResponse(DownloadAttachmentResponseModel responseModel) {
        assertNotNull(responseModel.getAttachment());
        assertEquals(1, responseModel.getAttachment().getAttachmentId());
        assertEquals("testFile", responseModel.getAttachment().getAttachmentName());
    }

    @Test(expected = NotFoundException.class)
    public void testDownloadNonExistingAttachment() {
        DownloadAttachmentRequest request = new DownloadAttachmentRequest(500,
                Mockito.mock(OutputStream.class));
        useCase.downloadAttachment(request, responseModel -> {

        });
    }
}
