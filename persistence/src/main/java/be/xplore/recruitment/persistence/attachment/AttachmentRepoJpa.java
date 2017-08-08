package be.xplore.recruitment.persistence.attachment;

import be.xplore.recruitment.domain.attachment.Attachment;
import be.xplore.recruitment.domain.attachment.AttachmentRepository;
import be.xplore.recruitment.domain.exception.CouldNotDownloadAttachmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Stijn Schack
 * @since 8/7/2017
 */
@Repository
@Transactional
public class AttachmentRepoJpa implements AttachmentRepository {
    private final FileManager fileManager;
    private final EntityManager entityManager;

    @Autowired
    public AttachmentRepoJpa(FileManager fileManager, EntityManager entityManager) {
        this.fileManager = fileManager;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Attachment> downloadAttachment(long attachmentId)
            throws CouldNotDownloadAttachmentException {
        Attachment attachment = entityManager.find(JpaAttachment.class, attachmentId).toAttachment();
        if (attachment != null) {
            trySetInputStream(attachment);
        }

        return Optional.ofNullable(attachment);
    }

    private void trySetInputStream(Attachment attachment) throws CouldNotDownloadAttachmentException {
        try {
            attachment.setInputStream(fileManager.downloadAttachment(attachment.getAttachmentName()));
        } catch (IOException e) {
            throw new CouldNotDownloadAttachmentException(e);
        }
    }
}
