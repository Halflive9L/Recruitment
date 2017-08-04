package be.xplore.recruitment.domain.applicant.attachment;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public interface AddApplicantAttachment {
    void addAttachment(AddApplicantAttachmentRequest request, Consumer<AddApplicantAttachmentResponseModel> response);
}
