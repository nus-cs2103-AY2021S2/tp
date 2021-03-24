package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineDate(null));
    }

    @Test
    public void constructor_invalidDeadlineDate_throwsIllegalArgumentException() {
        String invalidDeadlineDateLowerBound = "12-12-2019";
        String invalidDeadlineDateUpperBound = "01-01-2100";
        assertThrows(IllegalArgumentException.class, () ->
            new DeadlineDate(invalidDeadlineDateLowerBound));
        assertThrows(IllegalArgumentException.class, () ->
            new DeadlineDate(invalidDeadlineDateUpperBound));
    }

    @Test
    public void isValidDeadlineDate() {
        // null DeadlineDate
        assertThrows(NullPointerException.class, () -> DeadlineDate.isValidDeadlineDate(null));

        // invalid DeadlineDate
        assertFalse(DeadlineDate.isValidDeadlineDate("")); // empty string
        assertFalse(DeadlineDate.isValidDeadlineDate("12:12:2019")); // incorrect format

        // valid DeadlineDate
        assertTrue(DeadlineDate.isValidDeadlineDate("01-01-2020")); // 2020 is the lowest year accepted
        assertTrue(DeadlineDate.isValidDeadlineDate("12-12-2099")); // 2099 is the highest year accepted
        assertTrue(DeadlineDate.isValidDeadlineDate("10-11-2021")); // typical value

    }
}
