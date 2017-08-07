package be.xplore.recruitment.web.applicant.attachment;

import be.xplore.recruitment.domain.applicant.attachment.DownloadAttachmentResponseModel;
import be.xplore.recruitment.domain.attachment.Attachment;

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
    private String fileName;

    @Override
    public void accept(DownloadAttachmentResponseModel responseModel) {
        Attachment attachment = responseModel.getAttachment();
        this.fileName = attachment.getAttachmentName();
        try (InputStream in = attachment.getInputStream(); OutputStream out = responseModel.getOutputStream()) {
            copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getFileName() {
        return fileName;
    }

}