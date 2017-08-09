package be.xplore.recruitment.domain.interview.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;
import be.xplore.recruitment.domain.exception.CouldNotAddAttachmentException;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.interview.InterviewRepository;

import javax.inject.Named;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
@Named
public class AddInterviewAttachmentUseCase implements AddInterviewAttachment {
    private final InterviewRepository interviewRepository;

    public AddInterviewAttachmentUseCase(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    public void addAttachment(AddInterviewAttachmentRequest request,
                              Consumer<AttachmentResponseModel> response)
            throws NotFoundException, CouldNotAddAttachmentException {
        Attachment attachment = tryAddAttachment(request);
        response.accept(new AttachmentResponseModel(attachment));
    }

    private Attachment tryAddAttachment(AddInterviewAttachmentRequest request) {
        return interviewRepository.addAttachment(request.getInterviewId(), request.getAttachment()).
                orElseThrow(CouldNotAddAttachmentException::new);
    }
}
