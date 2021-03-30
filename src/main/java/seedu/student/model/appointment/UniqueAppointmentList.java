package seedu.student.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A nested list of appointments grouped by dates to facilitate UI display.
 * Appointments on the same date are grouped within the class {@code SameDateAppointmentList}.
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
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing
     * appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        boolean targetExists = false;
        LocalDate originalDate = target.getDate();
        LocalDate newDate = editedAppointment.getDate();
        boolean isSameDate = originalDate.equals(newDate);
        if (isSameDate) {
            //if it's the same date, find the matching SameDateAppointmentList and set the appointment there
            //just set the different time in original SameDateAppointmentList and UniqueAppointmentList
            for (SameDateAppointmentList s : internalList) {
                if (s.getDate().equals(originalDate)) {
                    s.setAppointment(target, editedAppointment);
                }
            }
        } else {
            //different date, need to delete from original SameDateAppointmentList, add to new SameDateAppointmentList,
            //edit in UniqueAppointmentList
            for (SameDateAppointmentList s : internalList) {
                if (s.getDate().equals(originalDate)) {
                    targetExists = true;
                }
            }
            if (targetExists) {
                remove(target);
            }
            add(editedAppointment);
        }
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        for (SameDateAppointmentList apptList : internalList) {
            apptList.remove(toRemove);
            if (apptList.isEmpty()) {
                internalList.remove(apptList);
                break;
            }
        }
    }

    /**
     * Replaces the contents of this list with a deep clone of {@code apptLists}.
     * {@code students} must not contain duplicate students.
     */
    public void setAppointments(List<SameDateAppointmentList> apptLists) {
        requireAllNonNull(apptLists);
        internalList.setAll(apptLists.stream().map(apptList -> apptList.deepClone()).collect(Collectors.toList()));
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
        if (other == this) {
            return true;
        }
        if (!(other instanceof UniqueAppointmentList)) {
            return false;
        }
        UniqueAppointmentList otherList = (UniqueAppointmentList) other;
        return internalList.stream().allMatch(apptList -> apptList
                .asUnmodifiableObservableList().stream().anyMatch(appt -> otherList.contains(appt)));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
