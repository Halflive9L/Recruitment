package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.attachment.DownloadAttachmentRequest;
import be.xplore.recruitment.domain.attachment.DownloadAttachmentResponseModel;
import be.xplore.recruitment.domain.exception.NotFoundException;

import javax.inject.Named;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
@Named
public class ReadApplicantAttachmentUseCase implements ReadApplicantAttachment {
    private final ApplicantRepository repository;

    public ReadApplicantAttachmentUseCase(ApplicantRepository repository) {
        this.repository = repository;
    }


    @Override
    public void listAllAttachmentsForApplicant(ListAllAttachmentsForApplicantRequest request,
                                               Consumer<List<ApplicantAttachmentResponseModel>> response) {
        List<Attachment> attachments = repository.findAllAttachmentsForApplicant(request.getApplicantId());
        response.accept(getResponseModelList(attachments));
    }

    private List<ApplicantAttachmentResponseModel> getResponseModelList(List<Attachment> attachments) {
        List<ApplicantAttachmentResponseModel> responseModel = new ArrayList<>();
        attachments.forEach(attachment -> responseModel.add(new ApplicantAttachmentResponseModel(attachment)));
        return responseModel;
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