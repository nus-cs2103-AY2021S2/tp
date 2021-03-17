package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeslotTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timeslot(null));
    }

    @Test
    public void constructor_invalidTimeslot_throwsIllegalArgumentException() {
        String invalidTimeslot = "";
        assertThrows(IllegalArgumentException.class, () -> new Timeslot(invalidTimeslot));
    }

    @Test
    public void isValidTimeslot() {
        // null Timeslot
        assertThrows(NullPointerException.class, () -> Timeslot.isValidTimeslot(null));

        // invalid Timeslot
        assertFalse(Timeslot.isValidTimeslot("")); // empty string
        assertFalse(Timeslot.isValidTimeslot(" ")); // spaces only
        assertFalse(Timeslot.isValidTimeslot("11:30")); // only 1 time
        assertFalse(Timeslot.isValidTimeslot("11:30 12:30")); // missing infix
        assertFalse(Timeslot.isValidTimeslot("11:30 - 12:30")); // wrong infix
        assertFalse(Timeslot.isValidTimeslot("11:30am to 12:30")); // wrong time format
        assertFalse(Timeslot.isValidTimeslot("1130 to 1230")); // wrong time format
        assertFalse(Timeslot.isValidTimeslot("23:30 to 24:30")); // invalid time
        assertFalse(Timeslot.isValidTimeslot("12:30 to 11:30")); // starting time after ending time
        assertFalse(Timeslot.isValidTimeslot("12:30 to 12:30")); // starting time same as ending time

        // valid Timeslot
        assertTrue(Timeslot.isValidTimeslot("12:00 to 14:00")); // valid timeslot
        assertTrue(Timeslot.isValidTimeslot("00:30 to 02:30")); // hour corner case
        assertTrue(Timeslot.isValidTimeslot("23:00 to 23:30")); // hour corner case
        assertTrue(Timeslot.isValidTimeslot("00:00 to 02:00")); // minute corner case
        assertTrue(Timeslot.isValidTimeslot("22:59 to 23:59")); // minute corner case
    }
}
