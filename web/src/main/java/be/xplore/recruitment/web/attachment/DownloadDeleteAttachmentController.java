package be.xplore.recruitment.web.attachment;

import be.xplore.recruitment.domain.attachment.DeleteAttachment;
import be.xplore.recruitment.domain.attachment.DeleteAttachmentRequest;
import be.xplore.recruitment.domain.attachment.DownloadAttachment;
import be.xplore.recruitment.domain.attachment.DownloadAttachmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
@RestController
public class DownloadDeleteAttachmentController {
    private final DownloadAttachment downloadAttachment;
    private final DeleteAttachment deleteAttachment;

    @Autowired
    public DownloadDeleteAttachmentController(DownloadAttachment downloadAttachment,
                                              DeleteAttachment deleteAttachment) {
        this.downloadAttachment = downloadAttachment;
        this.deleteAttachment = deleteAttachment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/attachment/{attachmentId}")
    public void downloadAttachment(@PathVariable("attachmentId") long attachmentId,
                                   HttpServletResponse response)
            throws IOException {
        DownloadAttachmentRequest request =
                new DownloadAttachmentRequest(attachmentId, response.getOutputStream());
        DownloadAttachmentPresenter presenter = new DownloadAttachmentPresenter(response);
        downloadAttachment.downloadAttachment(request, presenter);
        response.flushBuffer();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "api/v1/attachment/{attachmentId}")
    public ResponseEntity<JsonAttachment> deleteAttachment(@PathVariable("attachmentId") long attachmentId) {
        DeleteAttachmentRequest request = new DeleteAttachmentRequest(attachmentId);
        DeleteAttachmentPresenter presenter = new DeleteAttachmentPresenter();
        deleteAttachment.deleteAttachment(request, presenter);
        return presenter.getResponseEntity();
    }
}
