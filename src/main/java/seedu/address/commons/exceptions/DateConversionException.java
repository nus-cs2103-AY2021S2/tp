package seedu.address.commons.exceptions;

/**
 * Represents an error during conversion of date from one format to another.
 */
public class DateConversionException extends Exception {
    public DateConversionException(String message) {
        super(message);
    }
}
