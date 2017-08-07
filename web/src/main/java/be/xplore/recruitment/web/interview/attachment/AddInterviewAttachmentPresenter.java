package be.xplore.recruitment.web.interview.attachment;

import be.xplore.recruitment.domain.interview.attachment.InterviewAttachmentResponseModel;
import be.xplore.recruitment.web.attachment.JsonAttachment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class AddInterviewAttachmentPresenter implements Consumer<InterviewAttachmentResponseModel> {
    private ResponseEntity<JsonAttachment> responseEntity;

    @Override
    public void accept(InterviewAttachmentResponseModel interviewAttachmentResponseModel) {
        JsonAttachment responseBody = JsonAttachment.asJsonAttachment(interviewAttachmentResponseModel.getAttachment());
        responseEntity = new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    ResponseEntity<JsonAttachment> getResponseEntity() {
        return responseEntity;
    }
}
