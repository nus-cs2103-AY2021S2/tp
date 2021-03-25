package seedu.address.model.trip;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TripTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TripTime(null));
    }

    @Test
    public void isEqual() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        TripTime t1 = new TripTime(LocalTime.parse("2300", timeFormat));
        DateTimeFormatter otherTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
        TripTime t2 = new TripTime(LocalTime.parse("23:00", otherTimeFormat));
        assertTrue(t1.equals(t2));
    }
}
