package be.xplore.recruitment.web.interview.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.domain.interview.attachment.AddInterviewAttachment;
import be.xplore.recruitment.domain.interview.attachment.AddInterviewAttachmentRequest;
import be.xplore.recruitment.domain.interview.attachment.ListAllAttachmentsForInterviewRequest;
import be.xplore.recruitment.domain.interview.attachment.ReadInterviewAttachment;
import be.xplore.recruitment.web.attachment.JsonAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
@RestController
public class InterviewAttachmentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(InterviewAttachmentController.class);
    private final AddInterviewAttachment addInterviewAttachment;
    private final ReadInterviewAttachment readInterviewAttachment;

    @Autowired
    public InterviewAttachmentController(AddInterviewAttachment addInterviewAttachment,
                                         ReadInterviewAttachment readInterviewAttachment) {
        this.addInterviewAttachment = addInterviewAttachment;
        this.readInterviewAttachment = readInterviewAttachment;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/interview/{interviewId}/attachment",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JsonAttachment> uploadAttachment(@PathVariable long interviewId,
                                                           @RequestParam("attachment") MultipartFile file)
            throws IOException {
        LOGGER.info(file.getOriginalFilename());
        AddInterviewAttachmentRequest request = getAddAttachmentRequest(interviewId, file);
        AddInterviewAttachmentPresenter presenter = new AddInterviewAttachmentPresenter();
        return tryAddAttachment(request, presenter);
    }

    private ResponseEntity<JsonAttachment> tryAddAttachment(AddInterviewAttachmentRequest request,
                                                            AddInterviewAttachmentPresenter presenter) {
        try {
            addInterviewAttachment.addAttachment(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    private AddInterviewAttachmentRequest getAddAttachmentRequest(long interviewId, MultipartFile file)
            throws IOException {
        Attachment attachment = new Attachment(0, file.getOriginalFilename());
        attachment.setInputStream(file.getInputStream());
        AddInterviewAttachmentRequest request = createAddRequest(interviewId, attachment);
        return request;
    }

    private AddInterviewAttachmentRequest createAddRequest(long interviewId, Attachment attachment) {
        AddInterviewAttachmentRequest request = new AddInterviewAttachmentRequest();
        request.setAttachment(attachment);
        request.setInterviewId(interviewId);
        return request;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/interview/{interviewId}/attachment")
    public ResponseEntity<List<JsonAttachment>> listAllFilesForInterview(@PathVariable long interviewId) {
        ListAllAttachmentsForInterviewRequest request = new ListAllAttachmentsForInterviewRequest(interviewId);
        ListAllAttachmentsForInterviewPresenter presenter = new ListAllAttachmentsForInterviewPresenter();
        readInterviewAttachment.listAllAttachmentsForInterview(request, presenter);
        return presenter.getResponseEntity();
    }
}
