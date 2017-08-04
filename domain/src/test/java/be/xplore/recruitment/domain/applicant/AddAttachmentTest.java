package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachment;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentRequest;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentUseCase;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

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
    public void testAddAttachment() throws FileNotFoundException {
        AddApplicantAttachmentRequest request = new AddApplicantAttachmentRequest();
        request.setInput(getClass().getResourceAsStream("testPdf.pdf"));
        request.setAttachmentName("testFile");
        request.setApplicantId(1);
        useCase.addAttachment(request, addApplicantAttachmentResponseModel -> {

        });
        assertEquals(1, mockRepo.mockAttachments.size());
    }
}
