package be.xplore.recruitment.domain.exception;

/**
 * @author Lander
 * @since 26/07/2017
 */
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3421863299673788117L;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
