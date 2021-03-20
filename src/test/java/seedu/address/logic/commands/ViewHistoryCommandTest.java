package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewHistoryCommand.MESSAGE_ENTRY_FORMAT;
import static seedu.address.logic.commands.ViewHistoryCommand.MESSAGE_HEADER_SUCCESS;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalCommandHistoryEntries.getTypicalCommandHistory;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewHistoryCommand}.
 */
public class ViewHistoryCommandTest {
    private final Model typicalModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalCommandHistory());

    @Test
    public void equals() {
        final int lastFiveEntries = 5;

        ViewHistoryCommand viewAllCommand = new ViewHistoryCommand();
        ViewHistoryCommand viewFiveCommand = new ViewHistoryCommand(lastFiveEntries);

        // same object -> returns true
        assertTrue(viewAllCommand.equals(viewAllCommand));
        assertTrue(viewFiveCommand.equals(viewFiveCommand));

        // same values -> returns true
        assertTrue(viewAllCommand.equals(new ViewHistoryCommand()));
        assertTrue(viewFiveCommand.equals(new ViewHistoryCommand(lastFiveEntries)));

        // different types -> returns false
        assertFalse(viewFiveCommand.equals(lastFiveEntries));

        // null -> returns false
        assertFalse(viewAllCommand.equals(null));

        // different commands -> returns false
        assertFalse(viewAllCommand.equals(viewFiveCommand));
    }

    @Test
    public void execute_countOutOfRange_throwsCommandException() {
        final int tooBigCount = typicalModel.getCommandHistory().size() + 1;
        ViewHistoryCommand historyCommand = new ViewHistoryCommand(tooBigCount);

        String expectedMessage = String.format(ViewHistoryCommand.MESSAGE_INVALID_COUNT,
                1, typicalModel.getCommandHistory().size());

        assertCommandFailure(historyCommand, typicalModel, expectedMessage);
    }

    @Test
    public void execute_viewAllWithEmptyHistory_success() {
        Model modelEmptyHistory = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new CommandHistory());
        ViewHistoryCommand historyCommand = new ViewHistoryCommand();

        String expectedMessage = ViewHistoryCommand.MESSAGE_EMPTY_HISTORY;

        // No change to model here; it is LogicManager's responsibility to track command history
        // Tests for that are in LogicManager integration tests.
        ModelManager expectedModel = new ModelManager(modelEmptyHistory.getAddressBook(),
                modelEmptyHistory.getUserPrefs(), modelEmptyHistory.getCommandHistory());

        assertCommandSuccess(historyCommand, modelEmptyHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewAllWithHistory_success() {
        ViewHistoryCommand historyCommand = new ViewHistoryCommand();

        ReadOnlyCommandHistory history = typicalModel.getCommandHistory();
        String expectedMessage = buildCommandHistoryReversed(history, history.size());

        // No change to model here; it is LogicManager's responsibility to track command history
        // Tests for that are in LogicManager integration tests.
        ModelManager expectedModel = new ModelManager(typicalModel.getAddressBook(),
                typicalModel.getUserPrefs(), typicalModel.getCommandHistory());

        assertCommandSuccess(historyCommand, typicalModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewValidCountWithHistory_success() {
        ReadOnlyCommandHistory history = typicalModel.getCommandHistory();
        final int count = history.size() - 1; // valid count less than the size
        ViewHistoryCommand historyCommand = new ViewHistoryCommand(count);

        String expectedMessage = buildCommandHistoryReversed(history, count);

        // No change to model here; it is LogicManager's responsibility to track command history
        // Tests for that are in LogicManager integration tests.
        ModelManager expectedModel = new ModelManager(typicalModel.getAddressBook(),
                typicalModel.getUserPrefs(), typicalModel.getCommandHistory());

        assertCommandSuccess(historyCommand, typicalModel, expectedMessage, expectedModel);
    }

    private String buildCommandHistoryReversed(ReadOnlyCommandHistory history, int count) {
        StringBuilder msg = new StringBuilder(String.format(MESSAGE_HEADER_SUCCESS, count));
        for (int i = history.size() - 1; i >= history.size() - count; i--) {
            final int entryNum = i + 1;
            final String entryText = history.get(i).toString();
            msg.append(String.format(MESSAGE_ENTRY_FORMAT, entryNum, entryText));
        }
        return msg.toString();
    }
}
