package seedu.address.model.commandhistory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code CommandHistoryEntry}.
 */
public class CommandHistoryEntryTest {

    private static final String EMPTY_STRING = "";
    private static final String NON_EMPTY_STRING_1 = "asd";
    private static final String NON_EMPTY_STRING_2 = "history 5";

    @Test
    public void constructor_nonNull_doesNotThrow() {
        assertDoesNotThrow(() -> new CommandHistoryEntry(EMPTY_STRING));
        assertDoesNotThrow(() -> new CommandHistoryEntry(NON_EMPTY_STRING_1));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommandHistoryEntry(null));
    }

    @Test
    public void constructor_usesStringParameter() {
        // EP: empty string
        assertEquals(EMPTY_STRING, new CommandHistoryEntry(EMPTY_STRING).value);
        // EP: non-empty string
        assertEquals(NON_EMPTY_STRING_1, new CommandHistoryEntry(NON_EMPTY_STRING_1).value);
        assertEquals(NON_EMPTY_STRING_2, new CommandHistoryEntry(NON_EMPTY_STRING_2).value);
    }

    @Test
    public void equals_isEvaluatedByStringEquality() {
        assertEquals(new CommandHistoryEntry(NON_EMPTY_STRING_1), new CommandHistoryEntry(NON_EMPTY_STRING_1));
        assertEquals(new CommandHistoryEntry(NON_EMPTY_STRING_2), new CommandHistoryEntry(NON_EMPTY_STRING_2));
        assertNotEquals(new CommandHistoryEntry(NON_EMPTY_STRING_1), new CommandHistoryEntry(NON_EMPTY_STRING_2));
    }
}
