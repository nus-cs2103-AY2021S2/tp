package seedu.address.model.reminder;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an Reminder Tracker
 */
public interface ReadOnlyReminderTracker {

    /**
     * Returns an unmodifiable view of the reminder list.
     * This list will not contain any duplicate reminders.
     */
    ObservableList<Reminder> getReminderList();
}
