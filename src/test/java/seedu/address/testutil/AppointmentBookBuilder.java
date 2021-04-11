package seedu.address.testutil;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withContact("John", "Doe").build();}
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
     * Adds a new {@code Contact} to the {@code AddressBook} that we are building.
     */
    public AppointmentBookBuilder withAppointment(Appointment appointment) {
        appointmentBook.addAppointment(appointment);
        return this;
    }

    public AppointmentBook build() {
        return appointmentBook;
    }
}
