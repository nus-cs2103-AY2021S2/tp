package seedu.address.testutil;

import seedu.address.model.common.Description;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDate;

/**
 * A utility class to help with building Reminder objects.
 */
public class ReminderBuilder {

    public static final String DEFAULT_DESCRIPTION = "Maths Tuition Payment Due";
    public static final String DEFAULT_REMINDERDATE = "2021-06-01";

    private Description description;
    private ReminderDate reminderDate;

    /**
     * Creates an {@code ReminderBuilder} with the default details.
     */
    public ReminderBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        reminderDate = new ReminderDate(DEFAULT_REMINDERDATE);
    }

    /**
     * Initializes the ReminderBuilder with the data of {@code reminderToCopy}.
     */
    public ReminderBuilder(Reminder reminderToCopy) {
        description = reminderToCopy.getDescription();
        reminderDate = reminderToCopy.getReminderDate();
    }

    /**
     * Sets the {@code description} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code reminderDate} of the {@code Reminder} that we are building.
     */
    public ReminderBuilder withReminderDate(String reminderDate) {
        this.reminderDate = new ReminderDate(reminderDate);
        return this;
    }

    public Reminder build() {
        return new Reminder(description, reminderDate);
    }
}
