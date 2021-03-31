package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CapacityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Capacity(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        Integer invalidCapacity = -1;
        assertThrows(IllegalArgumentException.class, () -> new Capacity(invalidCapacity));
    }

    @Test
    public void isValidName() {
        // null capacity
        assertThrows(NullPointerException.class, () -> Capacity.isValidCapacity(null));

        // invalid name
        assertFalse(Capacity.isValidCapacity(-1)); // numbers below 0 only

        // valid name
        assertTrue(Capacity.isValidCapacity(3)); // numbers above 0 only
    }
}
