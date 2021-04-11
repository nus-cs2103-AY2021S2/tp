package seedu.module.commons.exceptions;

/**
 * Signals that integer given for index is negative or zero.
 */
public class IllegalIntegerException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalIntegerException(String message) {
        super(message);
    }
}
