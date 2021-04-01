package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the reminder-tracker level
 * Duplicates are not allowed (by .isSameReminder comparison)
 */
public class ReminderTracker implements ReadOnlyReminderTracker {

    private final ReminderList reminders;
    {
        reminders = new ReminderList();
    }

    public ReminderTracker() {
    }

    /**
     * Creates an ReminderTracker using the Reminders in the {@code toBeCopied}
     */
    public ReminderTracker(ReadOnlyReminderTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders.setReminders(reminders);
    }

    /**
     * Resets the existing data of this {@code ReminderTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyReminderTracker newData) {
        requireNonNull(newData);

        setReminders(newData.getReminderList());
    }

    /**
     * Returns true if a reminder with the same content as {@code reminder} exists in the reminder tracker.
     */
    public boolean hasReminder(Reminder reminder) {
        requireNonNull(reminder);
        return reminders.contains(reminder);
    }

    /**
     * Adds a reminder to the reminder tracker.
     * The reminder must not already exist in the reminder tracker.
     */
    public void addReminder(Reminder r) {
        reminders.add(r);
    }

    /**
     * Replaces the given reminder {@code target} in the list with {@code editedReminder}.
     * {@code target} must exist in the reminder tracker.
     * The reminder's content of {@code editedReminder} must not be the same as another existing reminder
     * in the reminder tracker.
     */
    public void setReminder(Reminder target, Reminder editedReminder) {
        requireNonNull(editedReminder);

        reminders.setReminder(target, editedReminder);
    }

    /**
     * Removes {@code key} from this {@code ReminderTracker}.
     * {@code key} must exist in the reminder tracker.
     */
    public void removeReminder(Reminder key) {
        reminders.remove(key);
    }

    @Override
    public String toString() {
        return reminders.asUnmodifiableObservableList().size() + " reminder(s)";
    }

    @Override
    public ObservableList<Reminder> getReminderList() {
        return reminders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderTracker // instanceof handles nulls
                && reminders.equals(((ReminderTracker) other).reminders));
    }

    @Override
    public int hashCode() {
        return reminders.hashCode();
    }
}
