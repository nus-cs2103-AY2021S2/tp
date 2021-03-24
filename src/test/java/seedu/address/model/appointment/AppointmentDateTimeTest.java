package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentDateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentDateTime(invalidDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null datetime
        assertThrows(NullPointerException.class, () -> AppointmentDateTime.isValidDateTime(null));

        // invalid datetime
        assertFalse(AppointmentDateTime.isValidDateTime("")); // empty string
        assertFalse(AppointmentDateTime.isValidDateTime(" ")); // spaces only
        assertFalse(AppointmentDateTime.isValidDateTime("^")); // only non-alphanumeric characters
        assertFalse(AppointmentDateTime.isValidDateTime("2021-03-55 10:00AM")); // invalid date
        assertFalse(AppointmentDateTime.isValidDateTime("2021-03-15 30:00AM")); // invalid time

        // valid datetime
        assertTrue(AppointmentDateTime.isValidDateTime("2021-03-24 10:00AM")); // YYYY-MM-DD
        assertTrue(AppointmentDateTime.isValidDateTime("2021/03/24 10:00AM")); // YYYY/MM/DD
    }
}
