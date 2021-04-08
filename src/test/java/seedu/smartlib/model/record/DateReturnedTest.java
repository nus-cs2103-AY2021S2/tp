package seedu.smartlib.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateReturnedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // for the constructor taking in a String
        assertThrows(NullPointerException.class, () -> new DateReturned((String) null));

        // for the constructor taking in a LocalDateTime
        assertThrows(NullPointerException.class, () -> new DateReturned((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DateReturned(invalidDate));
    }

    @Test
    public void isValidDate() {
        // EP: null date
        assertThrows(NullPointerException.class, () -> DateReturned.isValidDate(null));

        // EP: invalid dates
        assertFalse(DateReturned.isValidDate("")); // empty string
        assertFalse(DateReturned.isValidDate(" ")); // spaces only
        assertFalse(DateReturned.isValidDate("2020-01-31")); // date only
        assertFalse(DateReturned.isValidDate("2021-01-32T20:00:00")); // months with 31 days, more than 31 days
        assertFalse(DateReturned.isValidDate("2021-04-31T20:00:00")); // months with 30 days, more than 30 days
        assertFalse(DateReturned.isValidDate("2021-02-29T20:00:00")); // feb of non-leap year, more than 28 days

        // EP: valid dates
        assertTrue(DateReturned.isValidDate("2020-01-31T20:00:00")); // months with 31 days have 31 days
        assertTrue(DateReturned.isValidDate("2020-02-29T20:00:00")); // february of leap year has 29 days
    }

    @Test
    public void equals() {
        DateReturned dateReturned = new DateReturned("2021-01-31T20:00:00");
        DateReturned dateReturnedCopy = new DateReturned("2021-01-31T20:00:00");
        DateReturned dateReturned2 = new DateReturned("2021-03-31T20:00:00");

        // null -> returns false
        assertFalse(dateReturned.equals(null));

        // different types -> returns false
        assertFalse(dateReturned.equals(0.5f));
        assertFalse(dateReturned.equals(" "));

        // same object -> returns true
        assertTrue(dateReturned.equals(dateReturned));

        // same values -> returns true
        assertTrue(dateReturned.equals(dateReturnedCopy));

        // different values -> returns false
        assertFalse(dateReturned.equals(dateReturned2));
    }

    @Test
    public void tostring() {
        DateReturned dateReturned = new DateReturned("2021-01-31T20:00:00");
        DateReturned dateReturnedCopy = new DateReturned("2021-01-31T20:00:00");
        DateReturned dateReturned2 = new DateReturned("2021-03-31T20:00:00");

        // same object -> returns same toString value
        assertEquals(dateReturned.toString(), dateReturned.toString());

        // same values -> returns same toString value
        assertEquals(dateReturned.toString(), dateReturnedCopy.toString());

        // different values -> returns different toString value
        assertNotEquals(dateReturned.toString(), dateReturned2.toString());
    }

    @Test
    public void hashcode() {
        DateReturned dateReturned = new DateReturned("2021-01-31T20:00:00");
        DateReturned dateReturnedCopy = new DateReturned("2021-01-31T20:00:00");
        DateReturned dateReturned2 = new DateReturned("2021-03-31T20:00:00");

        // same object -> returns same hashcode
        assertEquals(dateReturned.hashCode(), dateReturned.hashCode());

        // same values -> returns same hashcode
        assertEquals(dateReturned.hashCode(), dateReturnedCopy.hashCode());

        // different values -> returns different hashcode
        assertNotEquals(dateReturned.hashCode(), dateReturned2.hashCode());
    }

}
