package be.xplore.recruitment.domain.attachment;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
public interface DeleteAttachment {
    void deleteAttachment(DeleteAttachmentRequest request, Consumer<AttachmentResponseModel> response);
}
