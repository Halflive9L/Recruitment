package be.xplore.recruitment.domain.interview.attachment;

import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;
import be.xplore.recruitment.domain.exception.CouldNotAddAttachmentException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public interface AddInterviewAttachment {
    void addAttachment(AddInterviewAttachmentRequest request, Consumer<AttachmentResponseModel> response)
            throws NotFoundException, CouldNotAddAttachmentException;
}
