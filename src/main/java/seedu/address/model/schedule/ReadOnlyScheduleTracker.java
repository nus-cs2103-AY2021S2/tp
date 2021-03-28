package seedu.address.model.schedule;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an Schedule Tracker
 */
public interface ReadOnlyScheduleTracker {

    /**
     * Returns an unmodifiable view of the schedule list.
     * This list will not contain any duplicate schedules.
     */
    ObservableList<Schedule> getScheduleList();
}
