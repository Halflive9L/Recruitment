package be.xplore.recruitment.web.applicant.attachment;

import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachment;
import be.xplore.recruitment.domain.applicant.attachment.AddApplicantAttachmentRequest;
import be.xplore.recruitment.domain.applicant.attachment.DownloadAttachmentRequest;
import be.xplore.recruitment.domain.applicant.attachment.ListAllAttachmentsForApplicantRequest;
import be.xplore.recruitment.domain.applicant.attachment.ReadApplicantAttachment;
import be.xplore.recruitment.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
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

    @RequestMapping(method = RequestMethod.POST, value = "/api/applicant/{applicantId}/file",
            consumes = "multipart/form-data")
    public ResponseEntity<JsonAttachment> uploadAttachment(@PathVariable long applicantId,
                                                           @RequestParam("file") MultipartFile file)
            throws IOException {
        System.out.println(file.getOriginalFilename());
        AddApplicantAttachmentRequest request = getAddAttachmentRequest(applicantId, file);
        AddApplicantAttachmentPresenter presenter = new AddApplicantAttachmentPresenter();
        try {
            addApplicantAttachment.addAttachment(request, presenter);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return presenter.getResponseEntity();
    }

    private AddApplicantAttachmentRequest getAddAttachmentRequest(long applicantId, MultipartFile file)
            throws IOException {
        AddApplicantAttachmentRequest request = new AddApplicantAttachmentRequest();
        request.setInput(file.getInputStream());
        request.setApplicantId(applicantId);
        request.setAttachmentName(file.getOriginalFilename());
        return request;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applicant/{applicantId}/files")
    public ResponseEntity<List<JsonAttachment>> listAllFilesForApplicant(@PathVariable long applicantId) {
        ListAllAttachmentsForApplicantRequest request = new ListAllAttachmentsForApplicantRequest(applicantId);
        ListAllAttachmentsForApplicantPresenter presenter = new ListAllAttachmentsForApplicantPresenter();
        readApplicantAttachment.listAllAttachmentsForApplicant(request, presenter);
        return presenter.getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/applicant/{applicantId}/{fileName:.+}")
    public void downloadFile(@PathVariable("applicantId") long applicantId,
                             @PathVariable("fileName") String fileName,
                             HttpServletResponse response)
            throws IOException {
        System.out.println(fileName);
        DownloadAttachmentRequest request =
                new DownloadAttachmentRequest(applicantId, fileName, response.getOutputStream());
        DownloadAttachmentPresenter presenter = new DownloadAttachmentPresenter();
        readApplicantAttachment.downloadAttachment(request, presenter);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + presenter.getFileName().replace(" ", "_"));

        response.flushBuffer();
    }}
