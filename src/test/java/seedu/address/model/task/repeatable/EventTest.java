package seedu.address.model.task.repeatable;

import seedu.address.model.task.Interval;
import seedu.address.model.task.completable.Deadline;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class EventTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        Interval interval = Interval.DAILY;
        assertThrows(NullPointerException.class, () -> new Event(null, interval, validDate));
        assertThrows(NullPointerException.class, () -> new Event("test", null, validDate));
        assertThrows(NullPointerException.class, () -> new Event("test", interval, null));
        assertThrows(NullPointerException.class, () -> new Event(null, interval, false, validDate));
        assertThrows(NullPointerException.class, () -> new Event("test", null, false, validDate));
        assertThrows(NullPointerException.class, () -> new Event("test", interval, null, validDate));
        assertThrows(NullPointerException.class, () -> new Event("test", interval, false, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        Interval validInterval = Interval.DAILY;
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription, validInterval, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription, validInterval, false, validDate));
        String invalidDescription2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription2, validInterval, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription2, validInterval, false, validDate));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Deadline.isValidDescription(null));

        // invalid description
        assertFalse(Deadline.isValidDescription("")); // empty string
        assertFalse(Deadline.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Deadline.isValidDescription("Blk 456, Den Road, #01-355"));
        assertTrue(Deadline.isValidDescription("-")); // one character
        assertTrue(Deadline.isValidDescription("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long description
    }
}
