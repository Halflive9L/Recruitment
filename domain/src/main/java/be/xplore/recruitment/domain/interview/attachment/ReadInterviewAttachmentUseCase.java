package be.xplore.recruitment.domain.interview.attachment;

import be.xplore.recruitment.domain.attachment.DownloadAttachmentRequest;
import be.xplore.recruitment.domain.attachment.DownloadAttachmentResponseModel;

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
                                               Consumer<List<InterviewAttachmentResponseModel>> presenter) {

    }

    @Override
    public void downloadAttachment(DownloadAttachmentRequest request, Consumer<DownloadAttachmentResponseModel> presenter) {

    }
}
