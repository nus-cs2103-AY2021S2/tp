package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class RecurrenceTest {
    @Test
    public void constructor_isNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Recurrence(null));
    }
    @Test
    public void constructor_invalidRecurrenceString_throwsIllegalArgumentException() {
        String invalidRecurrenceString = "";
        assertThrows(IllegalArgumentException.class, () -> new Recurrence(invalidRecurrenceString));
    }
    @Test
    public void constructor_validRecurrenceString_success() {
        //EP: valid recurrence string
        String validRecurrenceString = "daily";
        RecurrenceType recurrenceType = RecurrenceType.valueOf("daily");
        assertNotEquals(null, new Recurrence(validRecurrenceString).value);
        assertEquals(recurrenceType, new Recurrence(validRecurrenceString).getRecurrenceType());
    }

    @Test
    public void isValidRecurrenceTest() {
        //EP: empty string
        assertFalse(Recurrence.isValidRecurrence(""));

        //EP: non-empty string, invalid
        assertFalse(Recurrence.isValidRecurrence("hourly"));
        assertFalse(Recurrence.isValidRecurrence("monthly"));
        assertFalse(Recurrence.isValidRecurrence("yearly"));

        //EP: invalid strings, with valid substrings
        assertFalse(Recurrence.isValidRecurrence("daily daily"));
        assertFalse(Recurrence.isValidRecurrence("weekly!"));
        assertFalse(Recurrence.isValidRecurrence("daily biweekly"));

        //EP: valid strings, lowercase
        assertTrue(Recurrence.isValidRecurrence("daily"));
        assertTrue(Recurrence.isValidRecurrence("weekly"));
        assertTrue(Recurrence.isValidRecurrence("biweekly"));

        //EP: valid strings, uppercase (since recurrence case-insensitive)
        assertTrue(Recurrence.isValidRecurrence("DAILY"));
        assertTrue(Recurrence.isValidRecurrence("WEEKLY"));
        assertTrue(Recurrence.isValidRecurrence("BIWEEKLY"));
    }

    @Test
    public void equalsTest() {
        Recurrence dailyRecurrence = new Recurrence("daily");
        Recurrence dailyRecurrence2 = new Recurrence("daily");
        Recurrence weeklyRecurrence = new Recurrence("weekly");
        Recurrence biweeklyRecurrence = new Recurrence("biweekly");

        //EP: same object
        assertTrue(dailyRecurrence.equals(dailyRecurrence));

        //EP: different objects, same valid value
        assertTrue(dailyRecurrence.equals(dailyRecurrence2));

        //EP: different objects, different valid values
        assertFalse(weeklyRecurrence.equals(biweeklyRecurrence));
    }

    @Test
    public void hashcodeTest() {
        String recurrenceString = "daily";
        RecurrenceType recurrenceType = RecurrenceType.valueOf("daily");
        Recurrence recurrenceObject = new Recurrence("daily");

        assertEquals(Objects.hash(recurrenceString, recurrenceType), recurrenceObject.hashCode());
    }
}
