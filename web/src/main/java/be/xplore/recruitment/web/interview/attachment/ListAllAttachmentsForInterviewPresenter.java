package be.xplore.recruitment.web.interview.attachment;

import be.xplore.recruitment.domain.attachment.AttachmentResponseModel;
import be.xplore.recruitment.web.attachment.JsonAttachment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static be.xplore.recruitment.web.attachment.JsonAttachment.asJsonAttachment;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class ListAllAttachmentsForInterviewPresenter implements Consumer<List<AttachmentResponseModel>> {
    private ResponseEntity<List<JsonAttachment>> responseEntity;

    @Override
    public void accept(List<AttachmentResponseModel> interviewAttachmentResponseModels) {
        List<JsonAttachment> body = new ArrayList<>(interviewAttachmentResponseModels.size());
        interviewAttachmentResponseModels.forEach(interviewAttachmentResponseModel -> {
            JsonAttachment attachment = asJsonAttachment(interviewAttachmentResponseModel.getAttachment());
            body.add(attachment);
        });
        responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<List<JsonAttachment>> getResponseEntity() {
        return responseEntity;
    }
}
