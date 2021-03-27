package seedu.address.model.pool;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

public class TripDayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TripDay(null));
    }

    @Test
    public void isEqual() {
        DayOfWeek day1 = DayOfWeek.valueOf("MONDAY");
        TripDay mon = new TripDay(day1);
        DayOfWeek day2 = DayOfWeek.valueOf("MONDAY");
        TripDay mon2 = new TripDay(day2);
        assertTrue(mon.equals(mon2));
    }
}
