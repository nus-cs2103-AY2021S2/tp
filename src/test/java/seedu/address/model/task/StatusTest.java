package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "task done";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void hashcode_identicalStatusObjects_identicalHashcodes() {
        Status statusOne = new Status("done");
        Status statusTwo = new Status("done");

        assertEquals(statusOne.hashCode(), statusTwo.hashCode());
    }

    @Test
    public void hashcode_differentStatusObjects_differentHashcodes() {
        Status statusOne = new Status("done");
        Status statusTwo = new Status("not done");

        assertNotEquals(statusOne.hashCode(), statusTwo.hashCode());
    }

    @Test
    public void isDone() {
        Status statusOne = new Status("done");
        Status statusTwo = new Status("not done");

        assertTrue(statusOne.isDone());
        assertFalse(statusTwo.isDone());
    }

    @Test
    public void isValidStatus() {
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        assertFalse(Status.isValidStatus("in progress"));
        assertFalse(Status.isValidStatus("done "));
        assertFalse(Status.isValidStatus(" not done"));
        assertFalse(Status.isValidStatus("notdone"));

        assertTrue(Status.isValidStatus("done"));
        assertTrue(Status.isValidStatus("not done"));
    }
}
