package be.xplore.recruitment.domain.attachment;

import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.io.OutputStream;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
@Named
public class DownloadAttachmentUseCase implements DownloadAttachment {
    private AttachmentRepository repository;

    public DownloadAttachmentUseCase(AttachmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void downloadAttachment(DownloadAttachmentRequest request,
                                   Consumer<DownloadAttachmentResponseModel> response) throws NotFoundException {
        Attachment attachment = repository.downloadAttachment(request.getAttachmentId()).orElseThrow(() ->
                new NotFoundException("Attachment with ID: " + request.getAttachmentId() + " does not exist")
        );

        DownloadAttachmentResponseModel responseModel = getDownloadAttachmentResponseModel(attachment,
                request.getOutputStream());
        response.accept(responseModel);
    }

    private DownloadAttachmentResponseModel getDownloadAttachmentResponseModel(Attachment attachment,
                                                                               OutputStream outputStream) {
        return new DownloadAttachmentResponseModel(attachment, outputStream);
    }
}
