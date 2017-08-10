package be.xplore.recruitment.web.applicant.attachment;

import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachment;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentRequest;
import be.xplore.recruitment.domain.applicant.attachment.ListAllAttachmentsForApplicantRequest;
import be.xplore.recruitment.domain.applicant.attachment.ReadApplicantAttachment;
import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import be.xplore.recruitment.web.attachment.JsonAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class ApplicantAttachmentController {
    private final AddApplicantAttachment addApplicantAttachment;
    private final ReadApplicantAttachment readApplicantAttachment;

    @Autowired
    public ApplicantAttachmentController(AddApplicantAttachment addApplicantAttachment,
                                         ReadApplicantAttachment readApplicantAttachment) {
        this.addApplicantAttachment = addApplicantAttachment;
        this.readApplicantAttachment = readApplicantAttachment;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/applicant/{applicantId}/attachment",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JsonAttachment> uploadAttachment(@PathVariable long applicantId,
                                                           @RequestParam("attachment") MultipartFile file)
            throws IOException {
        AddApplicantAttachmentRequest request = getAddAttachmentRequest(applicantId, file);
        AddApplicantAttachmentPresenter presenter = new AddApplicantAttachmentPresenter();
        return uploadAttachment(request, presenter);
    }

    private ResponseEntity<JsonAttachment> uploadAttachment(AddApplicantAttachmentRequest request,
                                                            AddApplicantAttachmentPresenter presenter) {
        try {
            addApplicantAttachment.addAttachment(request, presenter);
            return presenter.getResponseEntity();
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private AddApplicantAttachmentRequest getAddAttachmentRequest(long applicantId, MultipartFile file)
            throws IOException {
        Attachment attachment = getAttachment(file);
        return createAddRequest(applicantId, attachment);
    }

    private AddApplicantAttachmentRequest createAddRequest(long applicantId, Attachment attachment) {
        AddApplicantAttachmentRequest request = new AddApplicantAttachmentRequest();
        request.setAttachment(attachment);
        request.setApplicantId(applicantId);
        return request;
    }

    private Attachment getAttachment(MultipartFile file) throws IOException {
        Attachment attachment = new Attachment(0, file.getOriginalFilename());
        attachment.setInputStream(file.getInputStream());
        return attachment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/applicant/{applicantId}/attachment")
    public ResponseEntity<List<JsonAttachment>> listAllFilesForApplicant(@PathVariable long applicantId) {
        ListAllAttachmentsForApplicantRequest request = new ListAllAttachmentsForApplicantRequest(applicantId);
        ListAllAttachmentsForApplicantPresenter presenter = new ListAllAttachmentsForApplicantPresenter();
        readApplicantAttachment.listAllAttachmentsForApplicant(request, presenter);
        return presenter.getResponseEntity();
    }
}
