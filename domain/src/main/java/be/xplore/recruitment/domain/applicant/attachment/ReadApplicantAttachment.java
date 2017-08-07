package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.attachment.DownloadAttachmentRequest;
import be.xplore.recruitment.domain.attachment.DownloadAttachmentResponseModel;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public interface ReadApplicantAttachment {

    void listAllAttachmentsForApplicant(ListAllAttachmentsForApplicantRequest request,
                                        Consumer<List<ApplicantAttachmentResponseModel>> response);

    void downloadAttachment(DownloadAttachmentRequest request, Consumer<DownloadAttachmentResponseModel> presenter);
}
