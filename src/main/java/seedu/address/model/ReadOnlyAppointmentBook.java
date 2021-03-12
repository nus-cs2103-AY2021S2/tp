package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;

/**
 * Unmodifiable view of an Appointment Book
 */
public interface ReadOnlyAppointmentBook {

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointments.
     */
    ObservableList<Appointment> getAppointmentList();

}
