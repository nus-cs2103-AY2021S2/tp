package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DATE_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_TWO;
import static seedu.address.testutil.TypicalReminders.MATHS_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalReminders.SCIENCE_TUITION_PAYMENT_REMINDER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ReminderBuilder;

public class ReminderTest {

    @Test
    public void equals() {
        // same values -> returns true
        Reminder reminderOneCopy = new ReminderBuilder(MATHS_TUITION_PAYMENT_REMINDER).build();
        assertTrue(MATHS_TUITION_PAYMENT_REMINDER.equals(reminderOneCopy));

        // same object -> returns true
        assertTrue(MATHS_TUITION_PAYMENT_REMINDER.equals(MATHS_TUITION_PAYMENT_REMINDER));

        // null -> returns false
        assertFalse(MATHS_TUITION_PAYMENT_REMINDER.equals(null));

        // different type -> returns false
        assertFalse(MATHS_TUITION_PAYMENT_REMINDER.equals(5));

        // different reminder -> returns false
        assertFalse(MATHS_TUITION_PAYMENT_REMINDER.equals(SCIENCE_TUITION_PAYMENT_REMINDER));

        // different description -> returns false
        Reminder editedReminderOne = new ReminderBuilder(MATHS_TUITION_PAYMENT_REMINDER)
                .withDescription(VALID_REMINDER_DESC_TWO).build();
        assertFalse(MATHS_TUITION_PAYMENT_REMINDER.equals(editedReminderOne));

        // different date -> returns false
        editedReminderOne = new ReminderBuilder(MATHS_TUITION_PAYMENT_REMINDER)
                .withReminderDate(VALID_REMINDER_DATE_TWO).build();
        assertFalse(MATHS_TUITION_PAYMENT_REMINDER.equals(editedReminderOne));
    }
}
