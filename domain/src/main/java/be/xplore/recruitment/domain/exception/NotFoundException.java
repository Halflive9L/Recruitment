package be.xplore.recruitment.domain.exception;

/**
 * @author Lander
 * @since 26/07/2017
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
