package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error where the number is not an unsigned integer.
 */
public class InvalidIntegerException extends ParseException {
    public InvalidIntegerException(String message) {
        super(message);
    }

    public InvalidIntegerException(String message, Throwable cause) {
        super(message, cause);
    }
}
