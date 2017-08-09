package be.xplore.recruitment.domain.interview.attachment;

import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public interface ReadInterviewAttachment {
    void listAllAttachmentsForInterview(ListAllAttachmentsForInterviewRequest request,
                                        Consumer<List<AttachmentResponseModel>> presenter);
}
