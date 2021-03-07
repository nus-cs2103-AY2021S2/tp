package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.appointment.Appointment;

public class AppointmentBook {
    private final List<Appointment> appointments;

    public AppointmentBook() {
        appointments = new ArrayList<>();
    }

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the app.
     *
     * @param appointment The appointment to check.
     * @return True if there is an appointment with the same identity, otherwise false.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the app.
     * The appointment must not already exist in the appointment book.
     *
     * @param appointment The appointment to be added.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
