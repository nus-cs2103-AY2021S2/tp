package seedu.student.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * TO EDIT
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
public class UniqueAppointmentList implements Iterable<SameDateAppointmentList> {

    private final ObservableList<SameDateAppointmentList> internalList = FXCollections.observableArrayList();
    private final ObservableList<SameDateAppointmentList> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(apptList -> apptList.contains(toCheck));
    }

    /**
     * Returns true if the list contains an appointment that overlaps with the given argument.
     */
    public boolean hasOverlappingAppointment(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(apptList -> apptList.hasOverlappingAppointment(toCheck));
    }

    /**
     * Adds an appointment to the list.
     * The student must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        for (SameDateAppointmentList apptList : internalList) {
            if (apptList.sameDate(toAdd)) {
                apptList.add(toAdd);
                return;
            }
        }
        SameDateAppointmentList apptList = new SameDateAppointmentList(toAdd.getDate());
        apptList.add(toAdd);
        internalList.add(apptList);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        // TODO
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        assert toRemove != null;
        for (SameDateAppointmentList apptList : internalList) {
            apptList.getAppointmentList().remove(toRemove);
        }
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setAppointments(List<SameDateAppointmentList> lists) {
        requireAllNonNull(lists);
        // TODO: validate list of SameDateAppointmentList
        // internalList.setAll(lists);
        internalList.clear();
        for (SameDateAppointmentList smdl : lists) {
            List<Appointment> listAppts = smdl.getAppointmentList();
            List<Appointment> listApptsCopy = new ArrayList<>(listAppts);
            SameDateAppointmentList smdlCopy = new SameDateAppointmentList(smdl.getDate());
            for (Appointment a : listApptsCopy) {
                smdlCopy.add(a);
            }
            internalList.add(smdlCopy);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<SameDateAppointmentList> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<SameDateAppointmentList> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentList // instanceof handles nulls
                && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
