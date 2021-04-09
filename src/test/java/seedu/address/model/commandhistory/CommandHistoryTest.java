package seedu.address.model.commandhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalCommandHistoryEntries;

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
    public void appendEntry_insertsCorrectEntry() {
        CommandHistory commandHistory;
        CommandHistoryEntry entry;

        commandHistory = new CommandHistory();
        entry = TypicalCommandHistoryEntries.HELP;
        commandHistory.appendEntry(entry);
        assertEquals(entry, commandHistory.get(0));

        commandHistory = new CommandHistory();
        entry = TypicalCommandHistoryEntries.HISTORY_ALL;
        commandHistory.appendEntry(entry);
        assertEquals(entry, commandHistory.get(0));
    }

    @Test
    public void constructor_takingListOfEntries_isMakingACopyOfTheList() {
        List<CommandHistoryEntry> typicalEntries1 = TypicalCommandHistoryEntries.getTypicalEntries();
        CommandHistory commandHistory = new CommandHistory(typicalEntries1);

        // modify the list
        typicalEntries1.clear();

        // check that the entries in CommandHistory have not changed
        List<CommandHistoryEntry> typicalEntries2 = TypicalCommandHistoryEntries.getTypicalEntries();
        assertEquals(typicalEntries2.size(), commandHistory.size());
        for (int i = 0; i < commandHistory.size(); i++) {
            CommandHistoryEntry entry = commandHistory.get(i);
            CommandHistoryEntry correspondingEntry = typicalEntries2.get(i);
            assertEquals(entry, correspondingEntry);
        }
    }

    @Test
    public void constructor_takingReadOnlyHistory_isMakingACopy() {
        CommandHistory typicalHistory = TypicalCommandHistoryEntries.getTypicalCommandHistory();
        CommandHistory copy = new CommandHistory(typicalHistory);

        assertEquals(typicalHistory.size(), copy.size());
        for (int i = 0; i < copy.size(); i++) {
            CommandHistoryEntry entry = copy.get(i);
            CommandHistoryEntry correspondingEntry = typicalHistory.get(i);
            assertEquals(entry, correspondingEntry);
        }
    }

    @Test
    public void equals() {
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

        // same object -> return true
        assertTrue(typicalHistory.equals(typicalHistory));

        // different type -> return false
        assertFalse(typicalHistory.equals("hi there"));

        // same entries -> return true
        assertTrue(typicalHistory.equals(copy));

        // one missing entry -> return false
        assertFalse(typicalHistory.equals(oneMissingEntry));

        // one extra entry -> return false
        assertFalse(typicalHistory.equals(oneExtraEntry));

        // different entries -> return false
        assertFalse(typicalHistory.equals(differentEntriesButSameSize));
    }

    @Test
    public void get_withInvalidIndex_throwsIndexOutOfBoundsException() {
        CommandHistory empty = new CommandHistory();
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(-1235));
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> empty.get(1));

        CommandHistory typical = TypicalCommandHistoryEntries.getTypicalCommandHistory();
        assertThrows(IndexOutOfBoundsException.class, () -> typical.get(typical.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> typical.get(-1));
    }

    @Test
    public void get_withValidIndex_returnsCorrectEntry() {
        CommandHistory commandHistory = TypicalCommandHistoryEntries.getTypicalCommandHistory();
        List<CommandHistoryEntry> typicalEntries = TypicalCommandHistoryEntries.getTypicalEntries();

        for (int i = 0; i < typicalEntries.size(); i++) {
            CommandHistoryEntry entry = commandHistory.get(i);
            CommandHistoryEntry correspondingEntry = typicalEntries.get(i);
            assertEquals(entry, correspondingEntry);
        }
    }

    @Test
    public void size_ofNewCommandHistory_isZero() {
        CommandHistory commandHistory = new CommandHistory();
        assertEquals(0, commandHistory.size());
    }
}
