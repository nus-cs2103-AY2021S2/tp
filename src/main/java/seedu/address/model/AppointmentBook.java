package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.tutor.Name;

/**
 * Wraps all data at the appointment-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AppointmentBook implements ReadOnlyAppointmentBook {

    private final AppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        appointments = new AppointmentList();
    }

    public AppointmentBook() {
    }

    /**
     * Creates an AppointmentBook using the Appointments in the {@code toBeCopied}
     */
    public AppointmentBook(ReadOnlyAppointmentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
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

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code appointment} exists in the appointment book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * @param name Name of tutor to check
     * @return True appointment list contains tutor in question.
     */
    public boolean hasAppointmentContainingTutor(Name name) {
        for (Appointment appointment : this.appointments) {
            if (appointment.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Changes all names of appointment related to tutor to the new name.
     * @param oldName Old name of tutor.
     * @param name New name of tutor
     */
    public void changeAllAppointmentsToName(Name oldName, Name name) {
        List<Appointment> tempAppointmentList = new ArrayList<>();
        for (Appointment appointment : this.appointments) {
            if (appointment.getName().equals(oldName)) {
                Appointment tempAppointment = new Appointment(name,
                        appointment.getSubject(), appointment.getTimeFrom(),
                        appointment.getTimeTo(), appointment.getLocation());
                tempAppointmentList.add(tempAppointment);
            } else {
                tempAppointmentList.add(appointment);
            }
        }
        setAppointments(tempAppointmentList);
    }


    /**
     * @param name Name of tutor to match.
     * @param timeFrom Time from of new appointment to be added
     * @param timeTo Time to of new appointment to be added
     * @return True if appointment cannot be added
     */
    public boolean doesAppointmentClash(Name name, AppointmentDateTime timeFrom,
                                        AppointmentDateTime timeTo) {

        List<Appointment> existingAppointments = findAllAppointmentsOfTutor(name);

        for (Appointment appointment : existingAppointments) {
            if (timeFrom.isTimeAfter(appointment.getTimeTo())
                    || timeTo.isTimeBefore(appointment.getTimeFrom())) {

            } else if (timeFrom.isTimeBefore(appointment.getTimeTo())) {
                if (!timeTo.isTimeBefore(appointment.getTimeFrom())) {
                    return true;
                }

            } else if (timeTo.isTimeAfter(appointment.getTimeFrom())) {
                if (!timeFrom.isTimeAfter(appointment.getTimeTo())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param name Name of tutor to match.
     * @return A list of appointments booked with tutor
     */
    public List<Appointment> findAllAppointmentsOfTutor(Name name) {
        return this.appointments.asUnmodifiableObservableList().stream().filter(
            appointment -> appointment.getName().equals(name)).collect(Collectors.toList());
    }


    /**
     * Adds a person to the appointment book.
     * The person must not already exist in the appointment book.
     */
    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the appointment book.
     * The person identity of {@code editedAppointment} must not be the same as another existing person
     * in the appointment book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code AppointmentBook}.
     * {@code key} must exist in the appointment book.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AppointmentBook}.
     * {@code key} must exist in the appointment book.
     */
    public Appointment removeAppointment(int key) {
        Appointment removedAppointment =
                appointments.getInternalUnmodifiableList().get(key);
        appointments.remove(key);
        return removedAppointment;
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
