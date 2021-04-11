package seedu.student.model.appointment.exceptions;

/**
 * Signals that the operation will result in Appointments of different dates being grouped together.
 */
public class DifferentDateAppointmentException extends RuntimeException {
    public DifferentDateAppointmentException() {
        super("Operation would result in appointments of different dates being grouped together");
    }
}
