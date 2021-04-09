package seedu.address.model.undoredo.exceptions;

/**
 * Thrown when a redo operations fails. Base class for more specific exceptions.
 */
public class RedoException extends RuntimeException {
    /**
     * Constructs an {@code RedoException} with the given message.
     *
     * @param msg The exception message.
     */
    public RedoException(String msg) {
        super(msg);
    }
}
