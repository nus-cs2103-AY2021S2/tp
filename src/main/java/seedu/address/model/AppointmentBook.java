package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;

/**
 * Wraps all data at the appointment-book level.
 * Duplicates are not allowed (by .isSameAppointment comparison).
 */
public class AppointmentBook implements ReadOnlyAppointmentBook {
    private UniqueAppointmentList appointments;
    private Stack<List<Appointment>> previousAppointmentLists = new Stack<>();

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        appointments = new UniqueAppointmentList();
    }

    public AppointmentBook() {
    }

    /**
     * Creates an Appointment Book using the Appointment in the {@code toBeCopied}
     */
    public AppointmentBook(ReadOnlyAppointmentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code AppointmentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentBook newData) {
        requireNonNull(newData);
        setAppointments(newData.getAppointmentList());
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
        previousAppointmentLists.push(new ArrayList<>(appointments.asUnmodifiableObservableList()));
        appointments.add(appointment);
    }


    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the appointment book.
     * The appointment identity of {@code editedAppointment} must not be the same as another
     * existing appointment in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);
        previousAppointmentLists.push(new ArrayList<>(appointments.asUnmodifiableObservableList()));
        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code AppointmentBook}.
     * {@code key} must exist in the appointment book.
     */
    public void removeAppointment(Appointment key) {
        previousAppointmentLists.push(new ArrayList<>(appointments.asUnmodifiableObservableList()));
        appointments.remove(key);
    }

    /**
     * Sorts appointment list using the specified comparator {@code comparator}.
     */
    public void sortAppointments(Comparator<Appointment> comparator) {
        appointments.sortAppointments(comparator);
    }

    /**
     * Undoes the previous add, delete or edit commands for appointments and returns a copy of the
     * previous appointment book.
     */
    public AppointmentBook undo() {
        AppointmentBook previousAppointmentBook = new AppointmentBook();
        previousAppointmentBook.setAppointments(previousAppointmentLists.pop());
        return previousAppointmentBook;
    }

    //// util methods

    @Override
    public String toString() {
        return appointments.asUnmodifiableObservableList().size() + " appointments";
        // TODO: refine later
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentBook // instanceof handles nulls
                && appointments.equals(((AppointmentBook) other).appointments));
    }

    @Override
    public int hashCode() {
        return appointments.hashCode();
    }
}
