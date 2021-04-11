package seedu.address.logic.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OperationFlagTest {

    private OperationFlag validAddOperationFlag = new OperationFlag(OperationFlag.ADD_FLAG);

    private OperationFlag validRemoveOperationFlag = new OperationFlag(OperationFlag.REMOVE_FLAG);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OperationFlag(null));
    }

    @Test
    public void constructor_invalidOperationFlag_throwsIllegalArgumentException() {
        String invalidOperationFlag = "";
        assertThrows(IllegalArgumentException.class, () -> new OperationFlag(invalidOperationFlag));
    }

    @Test
    public void constructor_validAddOperationFlag_success() {
        assertEquals(new OperationFlag("-a"), validAddOperationFlag);
    }

    @Test
    public void constructor_validRemoveOperationFlag_success() {
        assertEquals(new OperationFlag("-r"), validRemoveOperationFlag);
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(validAddOperationFlag.equals(validAddOperationFlag));
        assertTrue(validRemoveOperationFlag.equals(validRemoveOperationFlag));

        // same value -> returns true
        assertTrue(validAddOperationFlag.equals(new OperationFlag(OperationFlag.ADD_FLAG)));
        assertTrue(validRemoveOperationFlag.equals(new OperationFlag(OperationFlag.REMOVE_FLAG)));

        // diff type -> returns false
        assertFalse(validAddOperationFlag.equals(1));
        assertFalse(validAddOperationFlag.equals("Hello World!"));

        // null -> returns false
        assertFalse(validAddOperationFlag.equals(null));

        // diff values -> returns false
        assertFalse(validAddOperationFlag.equals(validRemoveOperationFlag));
    }

    @Test
    public void isValidOperationType() {
        // valid types
        assertTrue(OperationFlag.isValidOperationType(OperationFlag.ADD_FLAG));
        assertTrue(OperationFlag.isValidOperationType(OperationFlag.REMOVE_FLAG));

        // invalid types
        assertFalse(OperationFlag.isValidOperationType(""));
    }
}
