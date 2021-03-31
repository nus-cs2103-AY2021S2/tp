package seedu.address.testutil;

import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderTracker;

/**
 * A utility class to help with building ReminderTracker objects.
 */
public class ReminderTrackerBuilder {

    private ReminderTracker reminderTracker;

    public ReminderTrackerBuilder() {
        reminderTracker = new ReminderTracker();
    }

    public ReminderTrackerBuilder(ReminderTracker reminderTracker) {
        this.reminderTracker = reminderTracker;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ReminderTrackerBuilder withReminder(Reminder reminder) {
        reminderTracker.addReminder(reminder);
        return this;
    }

    public ReminderTracker build() {
        return reminderTracker;
    }
}
