package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.medical.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AppointmentBook {

    private List<Appointment> appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        appointments = new ArrayList<>();
    }

    public AppointmentBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AppointmentBook(List<Appointment> toBeCopied) {
        this();
        setAppointments(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasAppointment(Appointment appt) {
        requireNonNull(appt);
        return appointments.contains(appt);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addAppointment(Appointment appt) {
        appointments.add(appt);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppt) {
        requireNonNull(editedAppt);
        int i = appointments.indexOf(target);
        appointments.set(i, editedAppt);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void deleteAppointment(Appointment appt) {
        appointments.remove(appt);
    }

    //// util methods

    @Override
    public String toString() {
        return appointments.size() + " persons";
        // TODO: refine later
    }

    public List<Appointment> getAppointmentList() {
        return appointments;
    }
}
