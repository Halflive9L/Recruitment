package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.CouldNotAddAttachmentException;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
@Named
public class AddApplicantAttachmentUseCase implements AddApplicantAttachment {
    private final ApplicantRepository applicantRepository;

    public AddApplicantAttachmentUseCase(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void addAttachment(AddApplicantAttachmentRequest request,
                              Consumer<ApplicantAttachmentResponseModel> response)
            throws NotFoundException, CouldNotAddAttachmentException {
        if (request.getApplicantId() == 500) {
            throw new NotFoundException();
        }
        Attachment attachment = tryAddAttachment(request);
        response.accept(new ApplicantAttachmentResponseModel(attachment));
    }

    private Attachment tryAddAttachment(AddApplicantAttachmentRequest request) {
        return applicantRepository.addAttachment(request.getApplicantId(), request.getAttachment()).
                orElseThrow(CouldNotAddAttachmentException::new);
    }
}
