package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.model.schedule.exceptions.ScheduleNotFoundException;

public class ScheduleList implements Iterable<Schedule> {

    private final ObservableList<Schedule> internalList = FXCollections.observableArrayList();
    private final ObservableList<Schedule> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent schedule as the given argument.
     */
    public boolean contains(Schedule toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a schedule to the list.
     * The schedule must not already exist in the list.
     */
    public void add(Schedule toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateScheduleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the schedule {@code target} in the list with {@code editedSchedule}.
     * {@code target} must exist in the list.
     * The schedule identity of {@code editedSchedule} must not be
     * the same as another existing schedule in the list.
     */
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        requireAllNonNull(target, editedSchedule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ScheduleNotFoundException();
        }

        if (!target.equals(editedSchedule) && contains(editedSchedule)) {
            throw new DuplicateScheduleException();
        }

        internalList.set(index, editedSchedule);
    }

    /**
     * Removes the equivalent schedule from the list. (By schedule)
     * The schedule must exist in the list.
     */
    public void remove(Schedule toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ScheduleNotFoundException();
        }
    }

    /**
     * Removes the equivalent schedule from the list (must be present).
     *
     * @param index Index of schedule to remove (0-based)
     */
    public void remove(int index) {
        this.internalList.remove(index);
    }

    public void setSchedules(ScheduleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code schedules}.
     * {@code Schedule} must not contain duplicate schedules.
     */
    public void setSchedules(List<Schedule> schedules) {
        requireAllNonNull(schedules);
        if (!schedulesAreUnique(schedules)) {
            throw new DuplicateScheduleException();
        }

        internalList.setAll(schedules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     * Sorts by start time of each schedule.
     */
    public ObservableList<Schedule> asUnmodifiableObservableList() {
        Collections.sort(internalList, Comparator.comparing(a -> a.getTimeFrom().value));
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Schedule> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleList // instanceof handles nulls
                && internalList.equals(((ScheduleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Schedule} contains only unique schedules.
     */
    private boolean schedulesAreUnique(List<Schedule> schedules) {
        for (int i = 0; i < schedules.size() - 1; i++) {
            for (int j = i + 1; j < schedules.size(); j++) {
                if (schedules.get(i).equals(schedules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
