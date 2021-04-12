package seedu.address.logic.parser.exceptions;

/**
 * Represents a parse error where the index is not a number.
 */
public class NotANumberException extends ParseException {

    public NotANumberException(String message) {
        super(message);
    }

    public NotANumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
