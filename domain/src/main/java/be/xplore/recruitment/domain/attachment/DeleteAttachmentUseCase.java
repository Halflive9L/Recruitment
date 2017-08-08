package be.xplore.recruitment.domain.attachment;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
@Named
public class DeleteAttachmentUseCase implements DeleteAttachment {
    private final AttachmentRepository repository;

    public DeleteAttachmentUseCase(AttachmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAttachment(DeleteAttachmentRequest request, Consumer<AttachmentResponseModel> response) {
        Attachment attachment = repository.deleteAttachment(request.getAttachmentId())
                .orElseThrow(NotFoundException::new);
        response.accept(new AttachmentResponseModel(attachment));
    }
}
