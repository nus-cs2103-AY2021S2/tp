package seedu.address.testutil;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class to help with building Appointmentbook objects.
 * Example usage: <br>
 *     {@code AppointmentBook ab = new AppointmentBookBuilder().withAppointment("Meet Alex", "Meet Bob").build();}
 */
public class AppointmentBookBuilder {

    private AppointmentBook appointmentBook;

    public AppointmentBookBuilder() {
        appointmentBook = new AppointmentBook();
    }

    public AppointmentBookBuilder(AppointmentBook appointmentBook) {
        this.appointmentBook = appointmentBook;
    }

    /**
     * Adds a new {@code appointment} to the {@code AppointmentBook} that we are building.
     */
    public AppointmentBookBuilder withAppointment(Appointment appointment) {
        appointmentBook.addAppointment(appointment);
        return this;
    }

    public AppointmentBook build() {
        return appointmentBook;
    }
}
