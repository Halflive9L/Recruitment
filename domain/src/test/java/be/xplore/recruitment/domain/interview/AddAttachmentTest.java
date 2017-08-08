package be.xplore.recruitment.domain.interview;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.CouldNotAddAttachmentException;
import be.xplore.recruitment.domain.interview.attachment.AddInterviewAttachment;
import be.xplore.recruitment.domain.interview.attachment.AddInterviewAttachmentRequest;
import be.xplore.recruitment.domain.interview.attachment.AddInterviewAttachmentUseCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
public class AddAttachmentTest {
    private AddInterviewAttachment useCase;
    private MockInterviewRepo mockRepo;

    @Before
    public void initUseCase() {
        List<Interview> interviews = new ArrayList<>(1);
        interviews.add(Interview.builder().withInterviewId(1).build());
        mockRepo = new MockInterviewRepo(interviews);
        useCase = new AddInterviewAttachmentUseCase(mockRepo);
    }

    @Test
    public void testAddAttachment() throws IOException {
        Attachment attachment = new Attachment();
        attachment.setInputStream(Mockito.mock(InputStream.class));
        attachment.setAttachmentName("testPdf.pdf");
        AddInterviewAttachmentRequest request = new AddInterviewAttachmentRequest();
        request.setAttachment(attachment);
        request.setInterviewId(1);
        useCase.addAttachment(request, responseModel -> {
            assertEquals("testPdf.pdf", responseModel.getAttachment().getAttachmentName());
        });
        assertEquals(1, mockRepo.getAttachments().size());
    }

    @Test(expected = CouldNotAddAttachmentException.class)
    public void testAddAttachmentForNonExistingInterview() throws IOException {
        Attachment attachment = new Attachment();
        attachment.setInputStream(Mockito.mock(InputStream.class));
        attachment.setAttachmentName("testPdf.pdf");
        AddInterviewAttachmentRequest request = new AddInterviewAttachmentRequest();
        request.setAttachment(attachment);
        request.setInterviewId(500);
        useCase.addAttachment(request, addInterviewAttachmentResponseModel -> {

        });
    }
}
