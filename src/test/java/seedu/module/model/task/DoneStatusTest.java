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
    public void getIsDone() {
        //EP: true
        DoneStatus trueDoneStatus = new DoneStatus(true);
        assertEquals(trueDoneStatus.getIsDone(), true);

        //EP: false
        DoneStatus falseDoneStatus = new DoneStatus(false);
        assertEquals(falseDoneStatus.getIsDone(), false);
    }

    @Test
    public void equals() {
        DoneStatus firstDoneStatus = new DoneStatus(true);
        DoneStatus secondDoneStatus = new DoneStatus(true);
        DoneStatus thirdDoneStatus = new DoneStatus(false);
        String somethingElse = "Not Done Status";

        //EP: Same DoneStatus object
        assertTrue(firstDoneStatus.equals(firstDoneStatus));

        //EP: Different DoneStatus object, same boolean
        assertTrue(firstDoneStatus.equals(secondDoneStatus));

        //EP: Different DoneStatus object, different boolean
        assertFalse(firstDoneStatus.equals(thirdDoneStatus));

        //EP: Different object types
        assertFalse(firstDoneStatus.equals(somethingElse));
    }
}
