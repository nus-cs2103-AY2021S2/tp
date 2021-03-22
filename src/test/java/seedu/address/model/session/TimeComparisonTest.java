package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeComparisonTest {
    @Test
    public void isBefore() {
        assertFalse(new Time("00:00").isBefore(new Time("00:00"))); // same time
        assertFalse(new Time("00:01").isBefore(new Time("00:00"))); // is after
        assertTrue(new Time("00:00").isBefore(new Time("00:01"))); // is before
    }

    @Test
    public void isAfter() {
        assertFalse(new Time("12:00").isAfter(new Time("12:00"))); // same time
        assertFalse(new Time("12:00").isAfter(new Time("12:01"))); // is after
        assertTrue(new Time("12:01").isAfter(new Time("12:00"))); // is after
    }

    @Test
    public void isSame() {
        assertFalse(new Time("23:58").isSame(new Time("23:59"))); // is before
        assertFalse(new Time("23:59").isSame(new Time("23:58"))); // is after
        assertTrue(new Time("23:59").isSame(new Time("23:59"))); // is same
    }
}
