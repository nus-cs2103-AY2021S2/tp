package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation will result in a negative or zero duration for an appointment.
 */

public class NegativeOrZeroDurationException extends RuntimeException {
    public NegativeOrZeroDurationException() {
        super("Operation would result in an appointment with negative or zero duration");
    }
}
