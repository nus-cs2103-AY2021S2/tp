package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentConflictException;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;

/**
 * A list of appointments that enforces non-conflict between its elements, does not allow nulls and orders the
 * elements by their sorting criterion using {@code Appointment#compareTo(Appointment)}.
 * Appointments are considered non-conflicting by comparing using {@code Appointment#hasConflict(Appointment)}. '
 * As such, adding and updating of appointments uses Appointment#hasConflict(Appointment) so as to ensure that the
 * appointment being added or updated does not conflict with any existing appointments in the
 * NonConflictingAppointmentList.
 * Removal of an appointment uses Appointment#equals(Object) so as to ensure that the appointment with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Appointment#hasConflict(Appointment)
 */
public class NonConflictingAppointmentList implements Iterable<Appointment> {
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    //// accessors
    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains appointments that are in conflict with {@code toCheck}
     */
    public boolean hasConflict(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasConflict);
    }

    /**
     * Returns true if the list contains appointments that are in conflict with {@code toCheck}
     */
    public boolean hasConflictExcludingTarget(Appointment toExclude, Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(appt -> {
            return toCheck.hasConflict(appt) && !toExclude.equals(appt);
        });
    }

    /**
     * Returns true if {@code persons} contains non-conflicting appointments.
     */
    private boolean appointmentsAreNotInConflict(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).hasConflict(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    //// mutators: incorporates sorting
    /**
     * Adds an appointment to the list.
     * The appointment must not have conflicts with existing appointments in the list
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);

        if (hasConflict(toAdd)) {
            throw new AppointmentConflictException();
        }

        internalList.add(toAdd);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment {@code editedPerson} must not have conflicts with existing appointments
     * in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (hasConflictExcludingTarget(target, editedAppointment)) {
            throw new AppointmentConflictException();
        }

        internalList.set(index, editedAppointment);
        FXCollections.sort(internalList);
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }

        FXCollections.sort(internalList);
    }

    public void setAppointments(NonConflictingAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain conflicting appointments
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreNotInConflict(appointments)) {
            throw new AppointmentConflictException();
        }

        internalList.setAll(appointments);
        FXCollections.sort(internalList);
    }

    //// overriding methods
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NonConflictingAppointmentList // instanceof handles nulls
                && internalList.equals(((NonConflictingAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

}
