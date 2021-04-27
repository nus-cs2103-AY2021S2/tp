package seedu.student.model.appointment.exceptions;

/**
 * Signals that the operation will result in overlapping Appointments.
 */
public class OverlappingAppointmentException extends RuntimeException {
    public OverlappingAppointmentException() {
        super("Operation would result in overlapping appointments");
    }
}
