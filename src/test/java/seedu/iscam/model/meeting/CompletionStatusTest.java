package seedu.iscam.model.meeting;

import static seedu.iscam.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompletionStatusTest {

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CompletionStatus("Just any string"));
    }
}
