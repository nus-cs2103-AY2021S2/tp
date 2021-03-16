package seedu.address.model.person.passenger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TripTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TripTime(null));
    }

    @Test
    public void constructor_invalidTripTime_throwsIllegalArgumentException() {
        String invalidTripTime = "";
        assertThrows(IllegalArgumentException.class, () -> new TripTime(invalidTripTime));
    }

    @Test
    public void isEqual() {
        TripTime t1 = new TripTime("2300");
        TripTime t2 = new TripTime("2300");
        assertTrue(t1.equals(t2));
    }

    @Test
    public void isValidTripTime() {
        // null triptime
        assertThrows(NullPointerException.class, () -> TripTime.isValidTripTime(null));

        // invalid addresses
        assertFalse(TripTime.isValidTripTime("")); // empty string
        assertFalse(TripTime.isValidTripTime(" ")); // spaces only
        assertFalse(TripTime.isValidTripTime("asdd")); // not an int
        assertFalse(TripTime.isValidTripTime("2400")); // not a valid time
        assertFalse(TripTime.isValidTripTime("900")); // not a valid time
        assertFalse(TripTime.isValidTripTime("12:32")); // colons included

        // valid addresses
        assertTrue(TripTime.isValidTripTime("2359"));
        assertTrue(TripTime.isValidTripTime("0000"));
        assertTrue(TripTime.isValidTripTime("1421"));
    }
}
