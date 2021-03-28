package seedu.student.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.student.model.appointment.exceptions.OverlappingAppointmentException;

/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * An appointment is considered unique by comparing using {@code Appointment#isSameAppointment(Appointment)}.
 * As such, adding and updating of appointments uses Appointment#isSameAppointment(Appointment) for equality so as to
 * ensure that the appointment being added or updated is unique in terms of identity in the UniqueAppointmentList.
 * However, the removal of an appointment uses Appointment#equals(Object) so
 * as to ensure that the appointment with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Appointment#isSameAppointment(Appointment)
 */
public class SameDateAppointmentList implements Iterable<Appointment>, Comparable<SameDateAppointmentList> {
    private final LocalDate date;
    private final ObservableList<Appointment> internalList;
    private final ObservableList<Appointment> internalUnmodifiableList;

    /**
     * Creates a list of appointments on the same date.
     */
    public SameDateAppointmentList(LocalDate date) {

        this.date = date;
        internalList = FXCollections.observableArrayList();
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Returns true if the list contains an appointment that overlaps with the given argument.
     */
    public boolean hasOverlappingAppointment(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::doesTimeOverlap);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        } else if (hasOverlappingAppointment(toAdd)) {
            throw new OverlappingAppointmentException();
        } else if (!date.isEqual(toAdd.getDate())) {
            // to implement
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing appointment
     * in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) throws CommandException {
        requireAllNonNull(target, editedAppointment);
        int index = internalList.indexOf(target);
        if (index == -1) {
            //throw exception here?
            throw new CommandException("Target appointment does not exist!"); //this should be a constant, will define
            //the constant later
        }
        if (!target.isSameAppointment(editedAppointment) && contains(editedAppointment)) {
            //if the original appointment is not the same as the edited appointment, AND the list already contains
            //the duplicate appointment
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
        FXCollections.sort(internalList);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Appointment toRemove) {
        // TODO
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentList // instanceof handles nulls
                && internalList.equals(((SameDateAppointmentList) other).internalList));
    }

    public List<Appointment> getAppointmentList() {
        return internalList;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public boolean sameDate(Appointment toCheck) {
        return date.isEqual(toCheck.getDate());
    }

    @Override
    public int compareTo(SameDateAppointmentList o) {
        return date.compareTo(o.date);
    }
}
