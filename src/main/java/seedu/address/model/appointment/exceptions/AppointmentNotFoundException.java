package seedu.address.model.appointment.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super("Appointment not found");
    }
}
