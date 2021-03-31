package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.common.Description;

/**
 * Represents a Reminder in Tutor Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {

    // Identity fields
    private final Description description;
    private final ReminderDate reminderDate;

    /**
     * Every field must be present and not null.
     */
    public Reminder(Description description, ReminderDate reminderDate) {
        requireAllNonNull(description, reminderDate);
        this.description = description;
        this.reminderDate = reminderDate;
    }

    public Description getDescription() {
        return description;
    }

    public ReminderDate getReminderDate() {
        return reminderDate;
    }

    /**
     * Returns true if both reminder have the same description.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameReminder(Reminder otherReminder) {
        if (otherReminder == this) {
            return true;
        }

        return otherReminder != null
                && otherReminder.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Reminder)) {
            return false;
        }

        Reminder otherReminder = (Reminder) other;
        return otherReminder.getDescription().equals(getDescription())
                && otherReminder.getReminderDate().equals(getReminderDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, reminderDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Remind on: ")
                .append(getReminderDate());

        return builder.toString();
    }
}
