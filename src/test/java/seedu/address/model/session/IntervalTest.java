package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.session.Interval.isValidInterval;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class IntervalTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interval(null));
    }

    @Test
    public void constructor_invalidInterval_throwsIllegalArgumentException() {
        String invalidInterval1 = "0";
        String invalidInterval2 = "09";
        assertThrows(IllegalArgumentException.class, () -> new Interval(invalidInterval1));
        assertThrows(IllegalArgumentException.class, () -> new Interval(invalidInterval2));
    }

    @Test
    public void isValidIntervalTest() {
        assertThrows(NullPointerException.class, () -> isValidInterval(null));

        assertTrue(isValidInterval("1"));
        assertTrue(isValidInterval("10"));
        assertTrue(isValidInterval("100"));

        assertFalse(isValidInterval(" "));
        assertFalse(isValidInterval("0"));
        assertFalse(isValidInterval(" 1"));
        assertFalse(isValidInterval("one"));
        assertFalse(isValidInterval(".1"));
    }

    @Test
    public void equalsTest() {
        assertTrue(new Interval("1").equals(new Interval("1")));
        assertFalse(new Interval("1").equals(new Interval("10")));
    }
}
