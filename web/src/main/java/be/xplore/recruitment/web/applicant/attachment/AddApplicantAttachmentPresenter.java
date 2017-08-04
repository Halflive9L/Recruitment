package be.xplore.recruitment.web.applicant.attachment;

import be.xplore.recruitment.domain.applicant.attachment.ApplicantAttachmentResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class AddApplicantAttachmentPresenter implements Consumer<ApplicantAttachmentResponseModel> {
    private ResponseEntity<JsonAttachment> responseEntity;

    @Override
    public void accept(ApplicantAttachmentResponseModel applicantAttachmentResponseModel) {
        JsonAttachment responseBody = new JsonAttachment();
        responseBody.setAttachmentName(applicantAttachmentResponseModel.getAttachmentName());
        responseEntity = new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    public ResponseEntity<JsonAttachment> getResponseEntity() {
        return responseEntity;
    }
}
