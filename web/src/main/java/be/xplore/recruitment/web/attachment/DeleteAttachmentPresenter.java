package be.xplore.recruitment.web.attachment;

import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
public class DeleteAttachmentPresenter implements Consumer<AttachmentResponseModel> {
    private ResponseEntity<JsonAttachment> responseEntity;

    @Override
    public void accept(AttachmentResponseModel attachmentResponseModel) {
        JsonAttachment responseBody = JsonAttachment.asJsonAttachment(attachmentResponseModel.getAttachment());
        responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    ResponseEntity<JsonAttachment> getResponseEntity() {
        return responseEntity;
    }
}
