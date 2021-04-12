package seedu.address.logic.commandhistory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCommandHistoryEntries.getTypicalCommandHistory;
import static seedu.address.testutil.commandhistory.CommandHistoryTestUtil.getEntries;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;

/**
 * Contains unit tests for {@code SuppliedCommandHistorySelectorTest}.
 */
public class SuppliedCommandHistorySelectorTest {
    private CommandHistory emptyHistory;
    private CommandHistory typicalHistory;
    private SuppliedCommandHistorySelector typicalSelector;
    private SuppliedCommandHistorySelector emptySelector;

    @BeforeEach
    public void setUp() {
        emptyHistory = new CommandHistory();
        emptySelector = new SuppliedCommandHistorySelector(() -> emptyHistory);
        typicalHistory = getTypicalCommandHistory();
        typicalSelector = new SuppliedCommandHistorySelector(() -> typicalHistory);
    }

    @Test
    public void navigateToOnePastLast_onEmptyHistory_doesNotThrow() {
        assertDoesNotThrow(() -> emptySelector.navigateToOnePastLast());
    }

    @Test
    public void navigateToOnePastLast_thenSelectNextOnNonEmpty_returnsEmpty() {
        typicalSelector.navigateToOnePastLast();
        assertEquals(Optional.empty(), typicalSelector.selectNextUntilOnePastLast());
    }

    @Test
    public void navigateToOnePastLast_thenSelectPreviousOnNonEmpty_returnsLastEntry() {
        String lastEntry = typicalHistory.get(typicalHistory.size() - 1).value;

        typicalSelector.navigateToOnePastLast();
        assertEquals(Optional.of(lastEntry), typicalSelector.selectPreviousUntilFirst());
    }

    @Test
    public void selectNext_onEmpty_returnsEmpty() {
        assertEquals(Optional.empty(), emptySelector.selectNextUntilOnePastLast());
    }

    @Test
    public void selectNext_onNonEmptyFromLast_returnsEmpty() {
        List<CommandHistoryEntry> entries = getEntries(typicalHistory);
        // Navigate to last
        typicalSelector.navigateToOnePastLast();
        typicalSelector.selectPreviousUntilFirst();

        assertEquals(Optional.empty(), typicalSelector.selectNextUntilOnePastLast());
    }

    @Test
    public void selectNext_repeatedOnNonEmptyFromFirst_canSelectAllEntries() {
        List<CommandHistoryEntry> entries = getEntries(typicalHistory);
        typicalSelector.navigateToOnePastLast();
        // Navigate to first entry
        for (int i = entries.size() - 1; i >= 0; i--) {
            typicalSelector.selectPreviousUntilFirst();
        }

        // Check each entry from second until last
        for (int i = 1; i < entries.size(); i++) {
            Optional<String> entryText = Optional.of(entries.get(i).value);
            assertEquals(entryText, typicalSelector.selectNextUntilOnePastLast());
        }
    }

    @Test
    public void selectPrevious_onEmpty_returnsEmpty() {
        assertEquals(Optional.empty(), emptySelector.selectPreviousUntilFirst());
    }

    @Test
    public void selectPrevious_onNonEmptyFromFirst_returnsFirst() {
        List<CommandHistoryEntry> entries = getEntries(typicalHistory);
        typicalSelector.navigateToOnePastLast();
        // Navigate to first entry
        for (int i = entries.size() - 1; i >= 0; i--) {
            typicalSelector.selectPreviousUntilFirst();
        }

        // After selecting all the way to the first, the next call should return the first entry.
        assertEquals(Optional.of(entries.get(0).value), typicalSelector.selectPreviousUntilFirst());
    }

    @Test
    public void selectPrevious_repeatedOnNonEmptyFromLast_canSelectAllEntries() {
        List<CommandHistoryEntry> entries = getEntries(typicalHistory);

        // Check all entries in reverse
        typicalSelector.navigateToOnePastLast();
        for (int i = entries.size() - 1; i >= 0; i--) {
            Optional<String> entryText = Optional.of(entries.get(i).value);
            assertEquals(entryText, typicalSelector.selectPreviousUntilFirst());
        }
    }
}
