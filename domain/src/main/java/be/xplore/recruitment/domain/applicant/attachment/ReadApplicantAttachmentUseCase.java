package be.xplore.recruitment.domain.applicant.attachment;

import be.xplore.recruitment.domain.applicant.ApplicantRepository;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;

import javax.inject.Named;
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
                                               Consumer<List<AttachmentResponseModel>> response) {
        List<Attachment> attachments = repository.findAllAttachmentsForApplicant(request.getApplicantId());
        response.accept(getResponseModelList(attachments));
    }

    private List<AttachmentResponseModel> getResponseModelList(List<Attachment> attachments) {
        List<AttachmentResponseModel> responseModel = new ArrayList<>();
        attachments.forEach(attachment -> responseModel.add(new AttachmentResponseModel(attachment)));
        return responseModel;
    }
}