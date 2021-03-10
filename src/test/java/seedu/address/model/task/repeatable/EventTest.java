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
        LocalDate date = LocalDate.of(2020, 1, 1);
        Interval interval = Interval.DAILY;
        assertThrows(NullPointerException.class, () -> new Event(null, interval, date));
        assertThrows(NullPointerException.class, () -> new Event("test", null));
        assertThrows(NullPointerException.class, () -> new Event(null, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        LocalDate date = LocalDate.of(2020, 1, 1);
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription, date));
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription, date));
        String invalidDescription2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription2, date));
        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription2, date));
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
