package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.entry.EntryDate.DEFAULT_FORMATTER;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code EntryDate}.
 */
public class EntryDateTest {

    private String validDateString = "2021-01-01 12:12";
    private LocalDateTime validLocalDateTime = LocalDateTime.parse(validDateString, DEFAULT_FORMATTER);
    private EntryDate validEntryDate = new EntryDate(validDateString);

    @Test
    public void testGetDate() {
        assertEquals(validLocalDateTime, validEntryDate.getDate());
    }

    @Test
    public void testToString() {
        assertEquals("2021-01-01 12:12", validEntryDate.toString());
    }

    @Test
    public void isAfter_entryDateIsAfter_returnTrue() {
        assertTrue(validEntryDate.isAfter(new EntryDate("2020-12-31 12:12")));
        assertTrue(validEntryDate.isAfter(new EntryDate("2021-01-01 12:11")));
    }

    @Test
    public void isAfter_entryDateIsNotAfter_returnFalse() {
        assertFalse(validEntryDate.isAfter(new EntryDate("2021-01-02 12:12")));
        assertFalse(validEntryDate.isAfter(new EntryDate("2021-01-01 12:13")));
    }

    @Test
    public void isValidDate_validDate_returnTrue() {
        assertTrue(EntryDate.isValidDate("2021-01-01 12:12"));
        assertTrue(EntryDate.isValidDate("2021-01-01 00:00"));
        assertTrue(EntryDate.isValidDate("2024-02-29 12:12"));
    }

    @Test
    public void isValidDate_invalidMonth_returnFalse() {
        assertFalse(EntryDate.isValidDate("2021-13-01 12:12"));
        assertFalse(EntryDate.isValidDate("2021-0-01 12:12"));
    }

    @Test
    public void isValidDate_invalidDay_returnFalse() {
        assertFalse(EntryDate.isValidDate("2021-01-32 12:12"));
        assertFalse(EntryDate.isValidDate("2021-02-0 12:12"));
        assertFalse(EntryDate.isValidDate("2021-02-29 12:12"));
    }

    @Test
    public void isValidDate_invalidTime_returnFalse() {
        assertFalse(EntryDate.isValidDate("2021-01-01 25:00"));
        assertFalse(EntryDate.isValidDate("2021-01-01 23:60"));
    }

    @Test
    public void isValidDate_invalidFormat_returnFalse() {
        assertFalse(EntryDate.isValidDate("01-13-2021 12:12"));
        assertFalse(EntryDate.isValidDate("01-13-2021 12:12:12"));
        assertFalse(EntryDate.isValidDate("01132021 12:12"));
    }

    @Test
    public void equals() {
        EntryDate firstEntryDate = new EntryDate("2021-01-01 12:12");
        EntryDate secondEntryDate = new EntryDate("2021-02-02 13:13");

        // same object -> returns true
        assertTrue(firstEntryDate.equals(firstEntryDate));

        // same values -> returns true
        EntryDate firstEntryDateCopy = new EntryDate("2021-01-01 12:12");
        assertTrue(firstEntryDate.equals(firstEntryDateCopy));

        // different types -> returns false
        assertFalse(firstEntryDate.equals(1));

        // null -> returns false
        assertFalse(firstEntryDate.equals(null));

        // different contact -> returns false
        assertFalse(firstEntryDate.equals(secondEntryDate));
    }
}
