package be.xplore.recruitment.web.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.attachment.DownloadAttachmentResponseModel;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;

import static org.springframework.util.StreamUtils.copy;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
class DownloadAttachmentPresenter implements Consumer<DownloadAttachmentResponseModel> {
    private HttpServletResponse response;

    DownloadAttachmentPresenter(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void accept(DownloadAttachmentResponseModel responseModel) {
        setResponseHeaders(responseModel);
        Attachment attachment = responseModel.getAttachment();
        try (InputStream in = attachment.getInputStream(); OutputStream out = responseModel.getOutputStream()) {
            copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setResponseHeaders(DownloadAttachmentResponseModel responseModel) {
        response.setContentType("application/force-download");
        response.setHeader(HttpHeaders.TRANSFER_ENCODING, "binary");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + responseModel.
                        getAttachment().getAttachmentName().replace(" ", "_"));
    }
}