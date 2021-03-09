package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalVenues.VENUE1;
import static seedu.address.testutil.TypicalVenues.VENUE2;
import static seedu.address.testutil.TypicalVenues.VENUE3;
import static seedu.address.testutil.TypicalVenues.VENUE4;

import org.junit.jupiter.api.Test;

class VenueTest {

    @Test
    void isSameVenue() {
        // same object -> returns true
        assertTrue(VENUE1.isSameVenue(VENUE1));

        // null -> returns false
        assertFalse(VENUE1.isSameVenue(null));

        // same name -> returns true
        assertTrue(VENUE1.isSameVenue(VENUE2));

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
        assertNotEquals(VENUE2, VENUE1);
    }
}
