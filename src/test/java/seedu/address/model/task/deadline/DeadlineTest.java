package seedu.address.model.task.deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        assertThrows(NullPointerException.class, () -> new Deadline(null, validDate));
        assertThrows(NullPointerException.class, () -> new Deadline("test", null));
        assertThrows(NullPointerException.class, () -> new Deadline(null, validDate, false));
        assertThrows(NullPointerException.class, () -> new Deadline("test", validDate, null));
        assertThrows(NullPointerException.class, () -> new Deadline("test", null, false));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription, validDate, false));
        String invalidDescription2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription2, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription2, validDate, false));
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
        assertTrue(Deadline.isValidDescription("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }
}
