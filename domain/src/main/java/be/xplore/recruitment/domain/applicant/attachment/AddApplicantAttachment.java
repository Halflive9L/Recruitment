package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;
import be.xplore.recruitment.domain.exception.CouldNotAddAttachmentException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public interface AddApplicantAttachment {
    void addAttachment(AddApplicantAttachmentRequest request, Consumer<AttachmentResponseModel> response)
            throws NotFoundException, CouldNotAddAttachmentException;
}
