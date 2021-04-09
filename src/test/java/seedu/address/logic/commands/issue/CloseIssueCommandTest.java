package seedu.address.logic.commands.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.showIssueAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.issue.Issue;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CloseIssueCommand}.
 */
public class CloseIssueCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredIssueList_success() {
        Issue issueToClose = model.getFilteredIssueList().get(INDEX_FIRST.getZeroBased());
        CloseIssueCommand closeIssueCommand = new CloseIssueCommand(INDEX_FIRST);

        String expectedMessage = String.format(CloseIssueCommand.MESSAGE_CLOSE_ISSUE_SUCCESS, issueToClose);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.closeIssue(issueToClose);

        assertCommandSuccess(closeIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredIssueList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIssueList().size() + 1);
        CloseIssueCommand closeIssueCommand = new CloseIssueCommand(outOfBoundIndex);

        assertCommandFailure(closeIssueCommand, model, String.format(
                Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, model.getFilteredIssueList().size()));
    }

    @Test
    public void execute_validIndexFilteredIssueList_success() {
        showIssueAtIndex(model, INDEX_FIRST);

        Issue issueToClose = model.getFilteredIssueList().get(INDEX_FIRST.getZeroBased());
        CloseIssueCommand closeIssueCommand = new CloseIssueCommand(INDEX_FIRST);

        String expectedMessage = String.format(CloseIssueCommand.MESSAGE_CLOSE_ISSUE_SUCCESS, issueToClose);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.closeIssue(issueToClose);
        showNoIssue(expectedModel);

        assertCommandSuccess(closeIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredIssueList_throwsCommandException() {
        showIssueAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getIssueList().size());

        CloseIssueCommand closeIssueCommand = new CloseIssueCommand(outOfBoundIndex);

        assertCommandFailure(closeIssueCommand, model, String.format(
                Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, model.getFilteredIssueList().size()));
    }

    @Test
    public void equals() {
        CloseIssueCommand closeFirstCommand = new CloseIssueCommand(INDEX_FIRST);
        CloseIssueCommand closeSecondCommand = new CloseIssueCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(closeFirstCommand.equals(closeFirstCommand));

        // same values -> returns true
        CloseIssueCommand closeFirstCommandCopy = new CloseIssueCommand(INDEX_FIRST);
        assertTrue(closeFirstCommand.equals(closeFirstCommandCopy));

        // different types -> returns false
        assertFalse(closeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(closeFirstCommand.equals(null));

        // different resident -> returns false
        assertFalse(closeFirstCommand.equals(closeSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoIssue(Model model) {
        model.updateFilteredIssueList(p -> false);

        assertTrue(model.getFilteredIssueList().isEmpty());
    }
}
