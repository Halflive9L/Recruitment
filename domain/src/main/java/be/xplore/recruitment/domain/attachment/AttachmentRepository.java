package be.xplore.recruitment.domain.attachment;

import be.xplore.recruitment.domain.exception.CouldNotDownloadAttachmentException;

import java.util.Optional;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
public interface AttachmentRepository {
    Optional<Attachment> downloadAttachment(long attachmentId) throws CouldNotDownloadAttachmentException;

    Optional<Attachment> deleteAttachment(long attachmentId);
}
