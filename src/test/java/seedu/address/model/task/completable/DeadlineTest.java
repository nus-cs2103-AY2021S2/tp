package seedu.address.model.task.completable;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        assertThrows(NullPointerException.class, () -> new Deadline(null, validDate));
        assertThrows(NullPointerException.class, () -> new Deadline("test", null));
        assertThrows(NullPointerException.class, () -> new Deadline(null, false, validDate));
        assertThrows(NullPointerException.class, () -> new Deadline("test", null, validDate));
        assertThrows(NullPointerException.class, () -> new Deadline("test", false, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription, false, validDate));
        String invalidDescription2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription2, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription2, false, validDate));
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
