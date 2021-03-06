package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachment;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentRequest;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentUseCase;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class AddAttachmentTest {
    private AddApplicantAttachment useCase;
    private MockApplicantRepo mockRepo;

    @Before
    public void initUseCase() {
        mockRepo = new MockApplicantRepo();
        useCase = new AddApplicantAttachmentUseCase(mockRepo);
    }

    @Test
    public void testAddAttachment() throws IOException {
        Attachment attachment = createMockAttachment();
        AddApplicantAttachmentRequest request = createAddRequest(attachment, 1);
        useCase.addAttachment(request, addApplicantAttachmentResponseModel -> {
        });
        assertEquals(3, mockRepo.mockAttachments.size());
    }

    private AddApplicantAttachmentRequest createAddRequest(Attachment attachment, int applicantId) {
        AddApplicantAttachmentRequest request = new AddApplicantAttachmentRequest();
        request.setAttachment(attachment);
        request.setApplicantId(applicantId);
        return request;
    }

    private Attachment createMockAttachment() {
        Attachment attachment = new Attachment();
        attachment.setInputStream(Mockito.mock(InputStream.class));
        attachment.setAttachmentName("testPdf.pdf");
        return attachment;
    }

    @Test(expected = NotFoundException.class)
    public void testAddAttachmentForNonExistingApplicant() throws IOException {
        Attachment attachment = createMockAttachment();
        AddApplicantAttachmentRequest request = createAddRequest(attachment, 500);
        useCase.addAttachment(request, addApplicantAttachmentResponseModel -> {

        });
    }
}
