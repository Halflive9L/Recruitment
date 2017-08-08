package be.xplore.recruitment.web.attachment;

import be.xplore.recruitment.domain.attachment.DownloadAttachment;
import be.xplore.recruitment.domain.attachment.DownloadAttachmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RestController
public class DownloadAttachmentController {
    private DownloadAttachment downloadAttachment;

    @Autowired
    public DownloadAttachmentController(DownloadAttachment downloadAttachment) {
        this.downloadAttachment = downloadAttachment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/attachment/{attachmentId}")
    public void downloadFile(@PathVariable("attachmentId") long attachmentId,
                             HttpServletResponse response)
            throws IOException {
        System.out.println("Hello I am downloading for you! :)");
        DownloadAttachmentRequest request =
                new DownloadAttachmentRequest(attachmentId, response.getOutputStream());
        DownloadAttachmentPresenter presenter = new DownloadAttachmentPresenter(response);
        downloadAttachment.downloadAttachment(request, presenter);
        response.flushBuffer();
    }
}
