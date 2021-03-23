package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE2;
import static seedu.booking.testutil.TypicalVenues.VENUE3;
import static seedu.booking.testutil.TypicalVenues.VENUE4;
import static seedu.booking.testutil.TypicalVenues.VENUE5;

import org.junit.jupiter.api.Test;

class VenueTest {

    @Test
    void isSameVenue() {
        // same object -> returns true
        assertTrue(VENUE1.isSameVenue(VENUE1));

        // null -> returns false
        assertFalse(VENUE1.isSameVenue(null));

        // same name -> returns true
        assertTrue(VENUE1.isSameVenue(VENUE5));

        // different name -> returns false
        assertFalse(VENUE1.isSameVenue(VENUE3));

        // different name and remarks -> returns false
        assertFalse(VENUE1.isSameVenue(VENUE4));
    }

    @Test
    void equals() {
        // same object -> returns true
        assertTrue(VENUE1.equals(VENUE1));

        // null -> returns false
        assertFalse(VENUE1.equals(null));

        // different type -> returns false
        assertFalse(VENUE1.equals(5));

        // different venue -> returns false
        assertFalse(VENUE2.equals(VENUE1));
    }
}
