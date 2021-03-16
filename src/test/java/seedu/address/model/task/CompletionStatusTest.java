package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompletionStatusTest {

    @Test
    public void isValidCompletionStatus() {

        // invalid completionStatus
        assertFalse(CompletionStatus.isValidStatus("")); // empty string

        // valid completionStatus
        assertTrue(CompletionStatus.isValidStatus("INCOMPLETE"));
        assertTrue(CompletionStatus.isValidStatus("COMPLETE"));
    }
}
