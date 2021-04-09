package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateBorrowedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // for the constructor taking in a String
        assertThrows(NullPointerException.class, () -> new DateBorrowed((String) null));

        // for the constructor taking in a LocalDateTime
        assertThrows(NullPointerException.class, () -> new DateBorrowed((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DateBorrowed(invalidDate));
    }

    @Test
    public void isOverdue() {
        // EP: not overdue
        assertFalse(new DateBorrowed(LocalDateTime.now()).isOverdue());

        // EP: overdue
        assertTrue(new DateBorrowed("2020-02-29T20:00:00").isOverdue());
    }

    @Test
    public void isValidDate() {
        // EP: null date
        assertThrows(NullPointerException.class, () -> DateBorrowed.isValidDate(null));

        // EP: invalid dates
        assertFalse(DateBorrowed.isValidDate("")); // empty string
        assertFalse(DateBorrowed.isValidDate(" ")); // spaces only
        assertFalse(DateBorrowed.isValidDate("2020-01-31")); // date only
        assertFalse(DateBorrowed.isValidDate("2021-01-32T20:00:00")); // months with 31 days, more than 31 days
        assertFalse(DateBorrowed.isValidDate("2021-04-31T20:00:00")); // months with 30 days, more than 30 days
        assertFalse(DateBorrowed.isValidDate("2021-02-29T20:00:00")); // feb of non-leap year, more than 28 days

        // EP: valid dates
        assertTrue(DateBorrowed.isValidDate("2020-01-31T20:00:00")); // months with 31 days have 31 days
        assertTrue(DateBorrowed.isValidDate("2020-02-29T20:00:00")); // february of leap year has 29 days
    }

    @Test
    public void equals() {
        DateBorrowed dateBorrowed = new DateBorrowed("2021-01-31T20:00:00");
        DateBorrowed dateBorrowedCopy = new DateBorrowed("2021-01-31T20:00:00");
        DateBorrowed dateBorrowed2 = new DateBorrowed("2021-03-31T20:00:00");

        // null -> returns false
        assertFalse(dateBorrowed.equals(null));

        // different types -> returns false
        assertFalse(dateBorrowed.equals(0.5f));
        assertFalse(dateBorrowed.equals(" "));

        // same object -> returns true
        assertTrue(dateBorrowed.equals(dateBorrowed));

        // same values -> returns true
        assertTrue(dateBorrowed.equals(dateBorrowedCopy));

        // different values -> returns false
        assertFalse(dateBorrowed.equals(dateBorrowed2));
    }

    @Test
    public void tostring() {
        DateBorrowed dateBorrowed = new DateBorrowed("2021-01-31T20:00:00");
        DateBorrowed dateBorrowedCopy = new DateBorrowed("2021-01-31T20:00:00");
        DateBorrowed dateBorrowed2 = new DateBorrowed("2021-03-31T20:00:00");

        // same object -> returns same toString value
        assertEquals(dateBorrowed.toString(), dateBorrowed.toString());

        // same values -> returns same toString value
        assertEquals(dateBorrowed.toString(), dateBorrowedCopy.toString());

        // different values -> returns different toString value
        assertNotEquals(dateBorrowed.toString(), dateBorrowed2.toString());
    }

    @Test
    public void hashcode() {
        DateBorrowed dateBorrowed = new DateBorrowed("2021-01-31T20:00:00");
        DateBorrowed dateBorrowedCopy = new DateBorrowed("2021-01-31T20:00:00");
        DateBorrowed dateBorrowed2 = new DateBorrowed("2021-03-31T20:00:00");

        // same object -> returns same hashcode
        assertEquals(dateBorrowed.hashCode(), dateBorrowed.hashCode());

        // same values -> returns same hashcode
        assertEquals(dateBorrowed.hashCode(), dateBorrowedCopy.hashCode());

        // different values -> returns different hashcode
        assertNotEquals(dateBorrowed.hashCode(), dateBorrowed2.hashCode());
    }

}
