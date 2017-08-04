package be.xplore.recruitment.web.applicant.attachment;

import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachment;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
@RestController
public class ApplicantAttachmentController {
    AddApplicantAttachment addApplicantAttachment;

    @Autowired
    public ApplicantAttachmentController(AddApplicantAttachment addApplicantAttachment) {
        this.addApplicantAttachment = addApplicantAttachment;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/applicant/{applicantId}/file",
            consumes = "multipart/form-data")
    public ResponseEntity<JsonAttachment> uploadFile(@PathVariable long applicantId,
                                                     @RequestParam("file") MultipartFile file)
            throws IOException {
        System.out.println(file.getOriginalFilename());
        AddApplicantAttachmentRequest request = getAddAttachmentRequest(applicantId, file);
        AddApplicantAttachmentPresenter presenter = new AddApplicantAttachmentPresenter();
        addApplicantAttachment.addAttachment(request, presenter);
        return presenter.getResponseEntity();
    }

    private AddApplicantAttachmentRequest getAddAttachmentRequest(long applicantId,
                                                                  MultipartFile file)
            throws IOException {
        AddApplicantAttachmentRequest request = new AddApplicantAttachmentRequest();
        request.setInput(file.getInputStream());
        request.setApplicantId(applicantId);
        request.setAttachmentName(file.getOriginalFilename());
        return request;
    }
}
