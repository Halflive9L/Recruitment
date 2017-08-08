package be.xplore.recruitment.domain.exception;

/**
 * @author Stijn Schack
 * @since 8/4/2017
 */
public class CouldNotDownloadAttachmentException extends RuntimeException {
    private static final long serialVersionUID = -1290427921977718959L;

    public CouldNotDownloadAttachmentException(Throwable cause) {
        super(cause);
    }

    public CouldNotDownloadAttachmentException(String message) {
        super(message);
    }
}
