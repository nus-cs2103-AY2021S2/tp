package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.TypicalBookings.BOOKING1;
import static seedu.booking.testutil.TypicalBookings.BOOKING2;
import static seedu.booking.testutil.TypicalBookings.BOOKING3;
import static seedu.booking.testutil.TypicalBookings.BOOKING4;

import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    void isOverlapping() {
        // same object -> returns true
        assertTrue(BOOKING1.isOverlapping(BOOKING1));

        // null -> returns false
        assertFalse(BOOKING1.isOverlapping(null));

        // overlapping timings -> returns true
        assertTrue(BOOKING1.isOverlapping(BOOKING2));

        // different venue -> returns false
        assertFalse(BOOKING1.isOverlapping(BOOKING3));

        // different timings -> returns false
        assertFalse(BOOKING3.isOverlapping(BOOKING4));

        // different timings and venue -> returns false
        assertFalse(BOOKING1.isOverlapping(BOOKING4));
    }

    @Test
    void isId() {
        //same id -> returns true;
        assertTrue(BOOKING1.isId(new Id(0)));

        //different id -> returns false;
        assertFalse(BOOKING1.isId(new Id(1)));
    }
    @Test
    void equals() {
        // same object -> returns true
        assertTrue(BOOKING1.equals(BOOKING1));

        // null -> returns false
        assertFalse(BOOKING1.equals(null));

        // different type -> returns false
        assertFalse(BOOKING1.equals(5));

        // different venue -> returns false
        assertFalse(BOOKING1.equals(BOOKING2));
    }
}
