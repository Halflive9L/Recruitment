package be.xplore.recruitment.domain.applicant;

import be.xplore.recruitment.domain.applicant.attachment.ListAllAttachmentsForApplicantRequest;
import be.xplore.recruitment.domain.applicant.attachment.ReadApplicantAttachment;
import be.xplore.recruitment.domain.applicant.attachment.ReadApplicantAttachmentUseCase;
import be.xplore.recruitment.domain.attachment.Attachment;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public class ReadAttachmentTest {
    private ReadApplicantAttachment useCase;
    private MockApplicantRepo mockRepo;

    @Before
    public void initUseCase() {
        mockRepo = new MockApplicantRepo();
        useCase = new ReadApplicantAttachmentUseCase(mockRepo);
    }

    @Test
    public void testFindAllAttachments() {
        ListAllAttachmentsForApplicantRequest request = new ListAllAttachmentsForApplicantRequest(1);
        List<Attachment> attachments = new ArrayList<>();
        listAttachments(request, attachments);
        assertEquals(2, attachments.size());
    }

    private void listAttachments(ListAllAttachmentsForApplicantRequest request, List<Attachment> attachments) {
        useCase.listAllAttachmentsForApplicant(request,
                applicantAttachmentResponseModels -> {
                    applicantAttachmentResponseModels.forEach(applicantAttachmentResponseModel ->
                            attachments.add(applicantAttachmentResponseModel.getAttachment()));
                });
    }
}
