package seedu.address.model.session.exceptions;

/**
 * Error during the creation of a session {@link Session}.
 */
public class IllegalArgumentException extends Exception {
    public IllegalArgumentException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code SessionException} with the specified detail {@code message} and {@code cause}.
     */
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
