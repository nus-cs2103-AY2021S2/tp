package seedu.address.model.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class EntryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EntryDate(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EntryDate(invalidDate));
    }

    @Test
    public void isValidDate() {

        // valid dates
        assertTrue(EntryDate.isValidDate("2021-01-01 17:00"));
        assertTrue(EntryDate.isValidDate("1991-01-01 00:00"));
        assertTrue(EntryDate.isValidDate("2300-12-31 00:00"));

        // invalid dates
        assertFalse(EntryDate.isValidDate("2"));
        assertFalse(EntryDate.isValidDate("%"));
        assertFalse(EntryDate.isValidDate("hello"));
        assertFalse(EntryDate.isValidDate("22222-22"));
        assertFalse(EntryDate.isValidDate("2021 02 01"));
        assertFalse(EntryDate.isValidDate("2021-00-01 17:00"));
        assertFalse(EntryDate.isValidDate("2021-13-01 17:00"));
        assertFalse(EntryDate.isValidDate("2021-12-32 17:00"));
        assertFalse(EntryDate.isValidDate("2021-12-32 25:00"));
        assertFalse(EntryDate.isValidDate("2021-12-32 17:61"));
    }

    @Test
    public void equals() {
        EntryDate testEntryDate = new EntryDate("2021-02-02 00:00");

        // same date and time values -> return true
        assertTrue(testEntryDate.equals(new EntryDate("2021-02-02 00:00")));

        // same date value, different time value -> return false
        assertFalse(testEntryDate.equals(new EntryDate("2021-02-02 00:01")));

        // different date value, same time value -> return false
        assertFalse(testEntryDate.equals(new EntryDate("2021-02-01 00:00")));

        // different date value, different time value -> return false
        assertFalse(testEntryDate.equals(new EntryDate("2021-02-01 00:01")));
    }
}
