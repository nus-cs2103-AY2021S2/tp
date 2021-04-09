package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.AppointmentDateTime;

public class ReminderDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentDateTime(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ReminderDate(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null datetime
        assertThrows(NullPointerException.class, () -> ReminderDate.isValidDate(null));

        // invalid datetime
        assertFalse(ReminderDate.isValidDate("")); // empty string
        assertFalse(ReminderDate.isValidDate(" ")); // spaces only
        assertFalse(ReminderDate.isValidDate("^")); // only non-alphanumeric characters
        assertFalse(ReminderDate.isValidDate("2021-03-55")); // invalid date

        // valid datetime
        assertTrue(ReminderDate.isValidDate("2021-03-24")); // YYYY-MM-DD
    }
}
