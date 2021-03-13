package seedu.address.model.person.passenger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TripDayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TripDay(null));
    }

    @Test
    public void constructor_invalidTripDay_throwsIllegalArgumentException() {
        String invalidTripDay = "";
        assertThrows(IllegalArgumentException.class, () -> new TripDay(invalidTripDay));
    }

    @Test
    public void isEqual() {
        TripDay mon = new TripDay("monday");
        TripDay mon2 = new TripDay("MONDAY");
        assertTrue(mon.equals(mon2));
    }

    @Test
    public void isValidAddress() {
        // null tripday
        assertThrows(NullPointerException.class, () -> TripDay.isValidTripDay(null));

        // invalid addresses
        assertFalse(TripDay.isValidTripDay("")); // empty string
        assertFalse(TripDay.isValidTripDay(" ")); // spaces only
        assertFalse(TripDay.isValidTripDay("mondigs")); // not a weekday
        assertFalse(TripDay.isValidTripDay("mon")); // shortform

        // valid addresses
        assertTrue(TripDay.isValidTripDay("Monday")); // first letter is upper
        assertTrue(TripDay.isValidTripDay("sUnDay")); // random upper
        assertTrue(TripDay.isValidTripDay("friday")); // all lower
        assertTrue(TripDay.isValidTripDay("TUESDAY")); // all upper
    }
}
