package seedu.address.model.undoredo.exceptions;

/**
 * Thrown when an undo operations fails. Base class for more specific exceptions.
 */
public class UndoException extends RuntimeException {
    /**
     * Constructs an {@code UndoException} with the given message.
     *
     * @param msg The exception message.
     */
    public UndoException(String msg) {
        super(msg);
    }
}
