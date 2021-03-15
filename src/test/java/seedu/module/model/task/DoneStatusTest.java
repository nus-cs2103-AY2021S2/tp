package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DoneStatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DoneStatus((Boolean) null));
        assertThrows(NullPointerException.class, () -> new DoneStatus((String) null));
    }

    @Test
    public void constructor_invalidDoneStatus_throwsIllegalArgumentException() {
        String invalidDoneStatus = "invalid boolean string";
        assertThrows(IllegalArgumentException.class, () -> new DoneStatus(invalidDoneStatus));
    }

    @Test
    public void testDisplayUi() {
        assertEquals(new DoneStatus(true).displayUi(), "Done!\n");
        assertEquals(new DoneStatus(false).displayUi(), "Not Done!\n");
    }

    @Test
    public void equals() {
        DoneStatus firstDoneStatus = new DoneStatus(true);
        DoneStatus secondDoneStatus = new DoneStatus(true);
        DoneStatus thirdDoneStatus = new DoneStatus(false);
        String somethingElse = "Not Done Status";

        assertTrue(firstDoneStatus.equals(firstDoneStatus));
        assertTrue(firstDoneStatus.equals(secondDoneStatus));
        assertFalse(firstDoneStatus.equals(thirdDoneStatus));
        assertFalse(firstDoneStatus.equals(somethingElse));
    }
}
