package seedu.module.model.task;

import org.junit.jupiter.api.Test;

import static seedu.module.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneStatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DoneStatus((Boolean) null));
        assertThrows(NullPointerException.class, () -> new DoneStatus((String) null));
    }

    @Test
    public void testToString() {
        assertEquals(new DoneStatus(true), "Done!\n");
        assertEquals(new DoneStatus(false), "Not Done!\n");
    }
}
