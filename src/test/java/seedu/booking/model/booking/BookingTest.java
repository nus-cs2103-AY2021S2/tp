package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

        // overlapping timings -> returns false
        assertFalse(BOOKING1.isOverlapping(BOOKING2));

        // different venue -> returns false
        assertFalse(BOOKING1.isOverlapping(BOOKING3));

        // different timings -> returns false
        assertFalse(BOOKING3.isOverlapping(BOOKING4));

        // different timings and venue -> returns false
        assertFalse(BOOKING1.isOverlapping(BOOKING4));
    }

    @Test
    void hashCodeTest() {
        // same object -> hashCode is equal
        assertEquals(BOOKING1.hashCode(), BOOKING1.hashCode());

        // different venue -> returns false
        assertNotEquals(BOOKING1.hashCode(), BOOKING2.hashCode());
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


    @Test
    void isSameBooking() {
        // same object -> returns true
        assertTrue(BOOKING1.isSameBooking(BOOKING1));

        // null -> returns false
        assertFalse(BOOKING1.isSameBooking(null));

        // different venue -> returns false
        assertFalse(BOOKING1.isSameBooking(BOOKING2));
    }


    @Test
    void isExactlySameBooking() {
        // same object -> returns true
        assertTrue(BOOKING1.isExactlySameBooking(BOOKING1));

        // different venue -> returns false
        assertFalse(BOOKING1.isExactlySameBooking(BOOKING2));
    }
}
