package be.xplore.recruitment.web.applicant.attachment;

import be.xplore.recruitment.domain.applicant.attachment.ApplicantAttachmentResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static be.xplore.recruitment.web.applicant.attachment.JsonAttachment.asJsonAttachment;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class ListAllAttachmentsForApplicantPresenter implements Consumer<List<ApplicantAttachmentResponseModel>> {
    private ResponseEntity<List<JsonAttachment>> responseEntity;

    @Override
    public void accept(List<ApplicantAttachmentResponseModel> applicantAttachmentResponseModels) {
        List<JsonAttachment> body = new ArrayList<>(applicantAttachmentResponseModels.size());
        applicantAttachmentResponseModels.forEach(applicantAttachmentResponseModel -> {
            JsonAttachment attachment = asJsonAttachment(applicantAttachmentResponseModel.getAttachment());
            body.add(attachment);
        });
        responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<List<JsonAttachment>> getResponseEntity() {
        return responseEntity;
    }
}
