package be.xplore.recruitment.domain.interview.attachment;

import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;

import javax.inject.Named;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
@Named
public class ReadInterviewAttachmentUseCase implements ReadInterviewAttachment {
    @Override
    public void listAllAttachmentsForInterview(ListAllAttachmentsForInterviewRequest request,
                                               Consumer<List<AttachmentResponseModel>> presenter) {

    }
}
