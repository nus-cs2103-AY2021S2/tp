package seedu.address.model.appointment.exceptions;

import seedu.address.model.appointment.Appointment;

/**
 * Signals that the operation will result in conflicting appointments.
 * @see Appointment#hasConflict(Appointment)
 */

public class AppointmentConflictException extends RuntimeException {
    public AppointmentConflictException() {
        super("Operation would result in conflicting appointments");
    }
}
