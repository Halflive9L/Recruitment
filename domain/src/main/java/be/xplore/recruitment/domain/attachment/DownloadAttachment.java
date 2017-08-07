package be.xplore.recruitment.domain.attachment;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public interface DownloadAttachment {
    void downloadAttachment(DownloadAttachmentRequest request, Consumer<DownloadAttachmentResponseModel> presenter);
}
