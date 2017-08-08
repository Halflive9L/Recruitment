package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public interface ReadApplicantAttachment {

    void listAllAttachmentsForApplicant(ListAllAttachmentsForApplicantRequest request,
                                        Consumer<List<AttachmentResponseModel>> response);
}
