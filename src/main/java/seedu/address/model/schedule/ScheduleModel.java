package seedu.address.model.schedule;

import java.util.function.Predicate;

import javafx.collections.ObservableList;

public interface ScheduleModel {
    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULE = unused -> true;

    /**
     * Returns the AddressBook
     */
    ReadOnlyScheduleTracker getScheduleTracker();

    /**
     * Replaces address book data with the data in {@code scheduleTracker}.
     */
    void setScheduleTracker(ReadOnlyScheduleTracker scheduleTracker);

    /**
     * Returns an unmodifiable view of the filtered schedule list
     */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered schedule list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Schedule> predicate);

    /**
     * Checks if Schedule exists in schedule list.
     *
     * @param schedule Schedule to check
     * @return True if schedule is already in schedule list
     */
    boolean hasSchedule(Schedule schedule);

    /**
     * @param schedule Schedule to add (schedule must not already exist)
     */
    void addSchedule(Schedule schedule);

    /**
     * Removes schedule from schedule list.
     *
     * @param schedule Schedule to be removed must be present
     */
    void deleteSchedule(Schedule schedule);

    /**
     * Replaces the given schedule {@code target} with {@code editedSchedule}.
     * {@code target} must exist in the schedule tracker.
     * The {@code editedSchedule} must not be the same as another existing schedule in the schedule tracker.
     */
    void setSchedule(Schedule target, Schedule editedSchedule);
}
