package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the event-book level
 * Duplicates are not allowed (by .isSameEvent comparison)
 */
public class ScheduleTracker implements ReadOnlyScheduleTracker {

    private final ScheduleList schedules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        schedules = new ScheduleList();
    }

    public ScheduleTracker() {
    }

    /**
     * Creates an AppointmentBook using the Appointments in the {@code toBeCopied}
     */
    public ScheduleTracker(ReadOnlyScheduleTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules.setSchedules(schedules);
    }

    /**
     * Resets the existing data of this {@code AppointmentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyScheduleTracker newData) {
        requireNonNull(newData);

        setSchedules(newData.getScheduleList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code appointment} exists in the appointment book.
     */
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return schedules.contains(schedule);
    }

    /**
     * Adds a person to the appointment book.
     * The person must not already exist in the appointment book.
     */
    public void addSchedule(Schedule s) {
        schedules.add(s);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the appointment book.
     * The person identity of {@code editedAppointment} must not be the same as another existing person
     * in the appointment book.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireNonNull(editedSchedule);

        schedules.setSchedule(target, editedSchedule);
    }

    /**
     * Removes {@code key} from this {@code AppointmentBook}.
     * {@code key} must exist in the appointment book.
     */
    public void removeSchedule(Schedule key) {
        schedules.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AppointmentBook}.
     * {@code key} must exist in the appointment book.
     */
    public void removeSchedule(int key) {
        schedules.remove(key);
    }

    @Override
    public String toString() {
        return schedules.asUnmodifiableObservableList().size() + " appointments";
    }

    @Override
    public ObservableList<Schedule> getScheduleList() {
        return schedules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleTracker // instanceof handles nulls
                && schedules.equals(((ScheduleTracker) other).schedules));
    }

    @Override
    public int hashCode() {
        return schedules.hashCode();
    }
}
