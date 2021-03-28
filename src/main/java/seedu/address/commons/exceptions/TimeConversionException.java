package seedu.address.commons.exceptions;

/**
 * Represents an error during conversion of time from one format to another.
 */
public class TimeConversionException extends Exception {
    public TimeConversionException(String message) {
        super(message);
    }
}
