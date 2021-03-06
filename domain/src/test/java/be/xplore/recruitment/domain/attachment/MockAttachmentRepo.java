package be.xplore.recruitment.domain.attachment;

import be.xplore.recruitment.domain.exception.CouldNotDownloadAttachmentException;
import org.mockito.Mockito;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
public class MockAttachmentRepo implements AttachmentRepository {

    List<Attachment> attachments;

    public MockAttachmentRepo() {
        this.attachments = new ArrayList<>(1);
        Attachment attachment = new Attachment(1, "testFile");
        attachment.setInputStream(Mockito.mock(InputStream.class));
        attachments.add(attachment);
    }

    @Override
    public Optional<Attachment> downloadAttachment(long attachmentId) throws CouldNotDownloadAttachmentException {
        for (Attachment a : attachments) {
            if (a.getAttachmentId() == attachmentId) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Attachment> deleteAttachment(long attachmentId) {
        for (int i = 0; i < attachments.size(); i++) {
            Attachment a = attachments.get(i);
            if (checkAttachment(attachmentId, i)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    private boolean checkAttachment(long attachmentId, int i) {
        if (attachments.get(i).getAttachmentId() == attachmentId) {
            attachments.remove(i);
            return true;
        }
        return false;
    }
}
