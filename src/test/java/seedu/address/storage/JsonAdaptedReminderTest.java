package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedReminder.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReminders.MATHS_TUITION_PAYMENT_REMINDER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Description;
import seedu.address.model.reminder.ReminderDate;

public class JsonAdaptedReminderTest {
    private static final String INVALID_DATE = "2/5/2021";
    private static final String INVALID_DESCRIPTION = " ";

    private static final String VALID_DATE = MATHS_TUITION_PAYMENT_REMINDER.getReminderDate().toString();
    private static final String VALID_DESCRIPTION = MATHS_TUITION_PAYMENT_REMINDER.getDescription().toString();

    @Test
    public void toModelType_validReminderDetails_returnsReminder() throws Exception {
        JsonAdaptedReminder reminder = new JsonAdaptedReminder(MATHS_TUITION_PAYMENT_REMINDER);
        assertEquals(MATHS_TUITION_PAYMENT_REMINDER, reminder.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedReminder reminder = new JsonAdaptedReminder(VALID_DESCRIPTION, INVALID_DATE);
        String expectedMessage = ReminderDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, reminder::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedReminder reminder = new JsonAdaptedReminder(VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ReminderDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, reminder::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedReminder reminder = new JsonAdaptedReminder(INVALID_DESCRIPTION, VALID_DATE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, reminder::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedReminder reminder = new JsonAdaptedReminder(null, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, reminder::toModelType);
    }
}
