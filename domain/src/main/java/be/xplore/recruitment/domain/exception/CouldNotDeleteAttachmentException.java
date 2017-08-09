package be.xplore.recruitment.domain.exception;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */
@SuppressWarnings("serial")
public class CouldNotDeleteAttachmentException extends RuntimeException {
    public CouldNotDeleteAttachmentException(Throwable cause) {
        super(cause);
    }
}
