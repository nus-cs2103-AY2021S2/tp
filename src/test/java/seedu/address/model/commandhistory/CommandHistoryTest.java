package seedu.address.model.commandhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.commandhistory.CommandHistoryTestUtil.getEntries;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalCommandHistoryEntries;

/**
 * Contains unit tests for {@code CommandHistory}.
 */
public class CommandHistoryTest {

    @Test
    public void appendEntry_insertsAtEndOfHistory() {
        CommandHistory commandHistory = TypicalCommandHistoryEntries.getTypicalCommandHistory();

        final int originalSize = commandHistory.size();
        final int numToTest = 10;

        for (int i = originalSize; i < originalSize + numToTest; i++) {
            CommandHistoryEntry randomEntry = TypicalCommandHistoryEntries.getRandomEntry();
            commandHistory.appendEntry(randomEntry);
            assertEquals(randomEntry, commandHistory.get(i));
        }
    }

    @Test
    public void constructor_takingListOfEntries_isMakingACopyOfTheList() {
        List<CommandHistoryEntry> typicalEntries1 = TypicalCommandHistoryEntries.getTypicalEntries();
        CommandHistory commandHistory = new CommandHistory(typicalEntries1);

        // modify the list
        typicalEntries1.clear();

        // check that the entries in CommandHistory have not changed
        List<CommandHistoryEntry> typicalEntries2 = TypicalCommandHistoryEntries.getTypicalEntries();
        assertHistoryHasEntries(commandHistory, typicalEntries2);
    }

    @Test
    public void constructor_takingReadOnlyHistory_isMakingACopy() {
        CommandHistory typicalHistory = TypicalCommandHistoryEntries.getTypicalCommandHistory();
        CommandHistory copy = new CommandHistory(typicalHistory);

        assertHistoryHasEntries(copy, getEntries(typicalHistory));
    }

    @Test
    public void equals() {
        // Set up
        CommandHistory typicalHistory = TypicalCommandHistoryEntries.getTypicalCommandHistory();

        CommandHistory copy = new CommandHistory();
        for (int i = 0; i < typicalHistory.size(); i++) {
            copy.appendEntry(typicalHistory.get(i));
        }

        CommandHistory oneMissingEntry = new CommandHistory();
        for (int i = 0; i < typicalHistory.size() - 1; i++) {
            oneMissingEntry.appendEntry(typicalHistory.get(i));
        }

        CommandHistory oneExtraEntry = new CommandHistory();
        for (int i = 0; i < typicalHistory.size() - 1; i++) {
            oneExtraEntry.appendEntry(typicalHistory.get(i));
        }
        oneExtraEntry.appendEntry(TypicalCommandHistoryEntries.getRandomEntry());

        CommandHistory differentEntriesButSameSize = new CommandHistory();
        for (int i = 0; i < typicalHistory.size() - 1; i++) {
            differentEntriesButSameSize.appendEntry(TypicalCommandHistoryEntries.getRandomEntry());
        }

        // EP: same object -> return true
        assertTrue(typicalHistory.equals(typicalHistory));

        // EP: same entries -> return true
        assertTrue(typicalHistory.equals(copy));

        // EP: different type -> return false
        assertFalse(typicalHistory.equals("hi there"));

        // EP: one missing entry -> return false
        assertFalse(typicalHistory.equals(oneMissingEntry));

        // EP: one extra entry -> return false
        assertFalse(typicalHistory.equals(oneExtraEntry));

        // EP: different entries, same size -> return false
        assertFalse(typicalHistory.equals(differentEntriesButSameSize));
    }

    @Test
    public void get_withInvalidIndex_throwsIndexOutOfBoundsException() {
        CommandHistory empty = new CommandHistory();
        // EP: index < 0
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(-1235));
        // EP: index >= size
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(1));

        CommandHistory typical = TypicalCommandHistoryEntries.getTypicalCommandHistory();
        // EP: index >= size
        assertThrows(IndexOutOfBoundsException.class, () -> typical.get(typical.size()));
        // EP: index < 0
        assertThrows(IndexOutOfBoundsException.class, () -> typical.get(-1));
    }

    @Test
    public void get_withValidIndex_returnsCorrectEntry() {
        // Typical entries guarantees at least 3 entries
        List<CommandHistoryEntry> typicalEntries = TypicalCommandHistoryEntries.getTypicalEntries();
        CommandHistory commandHistory = new CommandHistory(typicalEntries);

        // BVA: test first index, last index and somewhere in-between (assumes at least 3 entries).
        final int firstIndex = 0;
        final int lastIndex = commandHistory.size() - 1;
        final int middleIndex = 1;
        assertEquals(typicalEntries.get(firstIndex), commandHistory.get(firstIndex));
        assertEquals(typicalEntries.get(lastIndex), commandHistory.get(lastIndex));
        assertEquals(typicalEntries.get(middleIndex), commandHistory.get(middleIndex));
    }

    @Test
    public void size_ofNewCommandHistory_isZero() {
        CommandHistory commandHistory = new CommandHistory();
        assertEquals(0, commandHistory.size());
    }

    /**
     * Asserts that the given command history has the same entries in the same order as a given list of entries.
     *
     * @param commandHistory The command history to check.
     * @param entries The entries to check against.
     */
    private void assertHistoryHasEntries(CommandHistory commandHistory, List<CommandHistoryEntry> entries) {
        assertEquals(entries.size(), commandHistory.size());
        for (int i = 0; i < commandHistory.size(); i++) {
            CommandHistoryEntry entry = commandHistory.get(i);
            CommandHistoryEntry correspondingEntry = entries.get(i);
            assertEquals(entry, correspondingEntry);
        }
    }
}
