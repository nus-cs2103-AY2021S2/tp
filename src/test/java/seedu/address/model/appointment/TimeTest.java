package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void testStringConversion() {
        assertEquals("9:00AM",
                new Time(LocalTime.parse("09:00")).toString());
        assertEquals("11:59PM",
                new Time(LocalTime.parse("23:59")).toString());
    }
}
