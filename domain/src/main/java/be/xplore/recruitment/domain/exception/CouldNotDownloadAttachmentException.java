package be.xplore.recruitment.domain.exception;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class CouldNotDownloadAttachmentException extends RuntimeException {
    public CouldNotDownloadAttachmentException(Throwable cause) {
        super(cause);
    }

    public CouldNotDownloadAttachmentException(String message) {
        super(message);
    }
}
