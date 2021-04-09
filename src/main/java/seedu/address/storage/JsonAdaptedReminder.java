package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Description;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDate;

public class JsonAdaptedReminder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reminder's %s field"
            + " is missing!";
    private final String description;
    private final String reminderDate;

    /**
     * Primary Constructor to create Json Adapted Reminder
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("description") String description,
                               @JsonProperty("reminderDate") String reminderDate) {
        this.description = description;
        this.reminderDate = reminderDate;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder reminder) {
        this.description = reminder.getDescription().value;
        this.reminderDate = reminder.getReminderDate().toStorageString();
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code
     * Reminder } object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Reminder toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (reminderDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ReminderDate.class.getSimpleName()));
        }

        if (!ReminderDate.isValidDate(reminderDate)) {
            throw new IllegalValueException(ReminderDate.MESSAGE_CONSTRAINTS);
        }

        final ReminderDate modelReminderDate = new ReminderDate(reminderDate);

        return new Reminder(modelDescription, modelReminderDate);
    }
}
