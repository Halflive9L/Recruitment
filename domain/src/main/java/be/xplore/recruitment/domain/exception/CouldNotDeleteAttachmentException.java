package be.xplore.recruitment.domain.exception;

/**
 * @author Stijn Schack
 * @since 8/8/2017
 */

public class CouldNotDeleteAttachmentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CouldNotDeleteAttachmentException(Throwable cause) {
        super(cause);
    }
}
