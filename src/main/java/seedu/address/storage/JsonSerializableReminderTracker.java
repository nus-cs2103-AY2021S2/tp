package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderTracker;

public class JsonSerializableReminderTracker {

    public static final String MESSAGE_DUPLICATE_REMINDER = "Reminder list contains duplicate reminders(s).";

    private final List<JsonAdaptedReminder> reminders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableReminderTracker} with the given reminders.
     */
    @JsonCreator
    public JsonSerializableReminderTracker(@JsonProperty("reminders") List<JsonAdaptedReminder> reminderList) {
        this.reminders.addAll(reminderList);
    }

    /**
     * Converts a given {@code ReadOnlyReminderTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableReminderTracker}.
     */
    public JsonSerializableReminderTracker(ReadOnlyReminderTracker source) {
        this.reminders.addAll(source.getReminderList().stream()
                .map(JsonAdaptedReminder::new).collect(Collectors.toList()));
    }


    /**
     * Converts this reminder tracker into the model's {@code ReminderTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ReminderTracker toModelType() throws IllegalValueException {
        ReminderTracker reminderTracker = new ReminderTracker();
        for (JsonAdaptedReminder jsonAdaptedReminder : reminders) {
            Reminder reminder = jsonAdaptedReminder.toModelType();
            if (reminderTracker.hasReminder(reminder)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_REMINDER);
            }
            reminderTracker.addReminder(reminder);
        }
        return reminderTracker;
    }
}
