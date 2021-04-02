package seedu.booking.logic.commands.intermediate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.TypicalBookingIntermediate.BK_INTER1;
import static seedu.booking.testutil.TypicalBookingIntermediate.BK_INTER2;

import org.junit.jupiter.api.Test;

public class AddBookingIntermediateTest {

    @Test
    void equals() {
        // same object -> returns true
        assertTrue(BK_INTER1.equals(BK_INTER1));

        // null -> returns false
        assertFalse(BK_INTER1.equals(BK_INTER2));

        // different type -> returns false
        assertFalse(BK_INTER1.equals(99));

        // different intermediate -> returns false
        assertFalse(BK_INTER1.equals(BK_INTER2));
    }

}
