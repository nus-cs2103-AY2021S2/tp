package seedu.iscam.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.model.meeting.DateTime.DATETIME_PATTERN;
import static seedu.iscam.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidFormat_throwsDateTimeParseException() {
        String invalidDateTime = "Just a string";
        assertThrows(DateTimeParseException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void isValidDateTimeStr() {
        // Invalid date and time -> returns false
        String notDateTimeStr = "Just a string";
        assertFalse(DateTime.isStringValidFormat(notDateTimeStr));

        // Current date and time -> returns true
        String currentDateTimeStr = LocalDateTime.now().format(DATETIME_PATTERN);
        assertTrue(DateTime.isStringValidDateTime(currentDateTimeStr));

        // 5 minutes into the past -> return false
        String pastDateTimeStr = LocalDateTime.now().minusMinutes(5).format(DATETIME_PATTERN);
        assertFalse(DateTime.isStringValidDateTime(pastDateTimeStr));

        // 5 minutes into the future -> returns true
        String futureDateTimeStr = LocalDateTime.now().plusMinutes(5).format(DATETIME_PATTERN);
        assertTrue(DateTime.isStringValidDateTime(futureDateTimeStr));
    }

    @Test
    public void equals() {
        DateTime dateTime = new DateTime(LocalDateTime.now().format(DATETIME_PATTERN));

        // Null -> returns false
        assertNotEquals(dateTime, null);

        // Same localDateTime -> returns true
        DateTime otherDateTime = new DateTime(dateTime.get().format(DATETIME_PATTERN));
        assertEquals(otherDateTime, dateTime);

        // Different localDateTime -> returns false
        otherDateTime = new DateTime(dateTime.get().plusMinutes(5).format(DATETIME_PATTERN));
        assertNotEquals(otherDateTime, dateTime);
    }
}
