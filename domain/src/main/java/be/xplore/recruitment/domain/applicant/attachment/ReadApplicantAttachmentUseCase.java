package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;

import javax.inject.Named;
import java.io.InputStream;
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
        List<String> attachments = repository.findAllAttachmentsForApplicant(request.getApplicantId());
        response.accept(getResponseModelList(attachments));
    }

    private List<ApplicantAttachmentResponseModel> getResponseModelList(List<String> attachments) {
        List<ApplicantAttachmentResponseModel> responseModel = new ArrayList<>();
        attachments.forEach(s -> responseModel.add(new ApplicantAttachmentResponseModel(s)));
        return responseModel;
    }

    @Override
    public void downloadAttachment(DownloadAttachmentRequest request,
                                   Consumer<DownloadAttachmentResponseModel> response) {
        InputStream inputStream = repository.downloadAttachment(request.getApplicantId(), request.getAttachmentName());
        DownloadAttachmentResponseModel responseModel = getDownloadAttachmentResponseModel(
                request.getAttachmentName(),
                inputStream, request.getOutputStream());
        response.accept(responseModel);
    }

    private DownloadAttachmentResponseModel getDownloadAttachmentResponseModel(String attachmentName,
                                                                               InputStream inputStream,
                                                                               OutputStream outputStream) {
        return new DownloadAttachmentResponseModel(attachmentName, inputStream, outputStream);
    }
}