package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachment;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentRequest;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentUseCase;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
        Attachment attachment = new Attachment();
        attachment.setInputStream(getClass().getResourceAsStream("testPdf.pdf"));
        attachment.setAttachmentName("testPdf.pdf");
        AddApplicantAttachmentRequest request = new AddApplicantAttachmentRequest();
        request.setAttachment(attachment);
        request.setApplicantId(1);
        useCase.addAttachment(request, addApplicantAttachmentResponseModel -> {

        });
        assertEquals(3, mockRepo.mockAttachments.size());
    }

    @Test(expected = NotFoundException.class)
    public void testAddAttachmentForNonExistingApplicant() throws IOException {
        Attachment attachment = new Attachment();
        attachment.setInputStream(getClass().getResourceAsStream("testPdf.pdf"));
        attachment.setAttachmentName("testPdf.pdf");
        AddApplicantAttachmentRequest request = new AddApplicantAttachmentRequest();
        request.setAttachment(attachment);
        request.setApplicantId(500);
        useCase.addAttachment(request, addApplicantAttachmentResponseModel -> {

        });
    }
}
