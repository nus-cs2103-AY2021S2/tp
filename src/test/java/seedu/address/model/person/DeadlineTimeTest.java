package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineTime(null));
    }

    @Test
    public void constructor_invalidDeadlineTime_throwsIllegalArgumentException() {
        String invalidDeadlineTimeLowerBound = "23:60";
        String invalidDeadlineTimeUpperBound = "24:00";
        assertThrows(IllegalArgumentException.class, ()
            -> new DeadlineTime(invalidDeadlineTimeLowerBound));
        assertThrows(IllegalArgumentException.class, ()
            -> new DeadlineTime(invalidDeadlineTimeUpperBound));
    }

    @Test
    public void isValidDeadlineDate() {
        // null DeadlineDate
        assertThrows(NullPointerException.class, () -> DeadlineTime.isValidDeadlineTime(null));

        // invalid DeadlineDate
        assertFalse(DeadlineTime.isValidDeadlineTime("")); // empty string
        assertFalse(DeadlineTime.isValidDeadlineTime("12:20pm")); // incorrect format

        // valid DeadlineDate
        assertTrue(DeadlineTime.isValidDeadlineTime("00:00")); // 00:00 is the lowest time accepted
        assertTrue(DeadlineTime.isValidDeadlineTime("23:59")); // 23:59 is the highest TIME accepted
        assertTrue(DeadlineTime.isValidDeadlineTime("12:12")); // typical value

    }
}
