package seedu.smartlib.commons.exceptions;

/**
 * Represents an error during conversion of data from one format to another.
 */
public class DataConversionException extends Exception {

    /**
     * A constructor for the DataConversionException.
     *
     * @param cause should contain relevant information on the cause(s) of failure
     */
    public DataConversionException(Exception cause) {
        super(cause);
    }

}
