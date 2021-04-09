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
 * {@code DeleteIssueCommand}.
 */
public class DeleteIssueCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredIssueList_success() {
        Issue issueToDelete = model.getFilteredIssueList().get(INDEX_FIRST.getZeroBased());
        DeleteIssueCommand deleteIssueCommand = new DeleteIssueCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteIssueCommand.MESSAGE_DELETE_ISSUE_SUCCESS, issueToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteIssue(issueToDelete);

        assertCommandSuccess(deleteIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredIssueList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIssueList().size() + 1);
        DeleteIssueCommand deleteIssueCommand = new DeleteIssueCommand(outOfBoundIndex);

        assertCommandFailure(deleteIssueCommand, model, String.format(
                Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, model.getFilteredIssueList().size()));
    }

    @Test
    public void execute_validIndexFilteredIssueList_success() {
        showIssueAtIndex(model, INDEX_FIRST);

        Issue issueToDelete = model.getFilteredIssueList().get(INDEX_FIRST.getZeroBased());
        DeleteIssueCommand deleteIssueCommand = new DeleteIssueCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteIssueCommand.MESSAGE_DELETE_ISSUE_SUCCESS, issueToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteIssue(issueToDelete);
        showNoIssue(expectedModel);

        assertCommandSuccess(deleteIssueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredIssueList_throwsCommandException() {
        showIssueAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getIssueList().size());

        DeleteIssueCommand deleteIssueCommand = new DeleteIssueCommand(outOfBoundIndex);

        assertCommandFailure(deleteIssueCommand, model, String.format(
                Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, model.getFilteredIssueList().size()));
    }

    @Test
    public void equals() {
        DeleteIssueCommand deleteFirstCommand = new DeleteIssueCommand(INDEX_FIRST);
        DeleteIssueCommand deleteSecondCommand = new DeleteIssueCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteIssueCommand deleteFirstCommandCopy = new DeleteIssueCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different resident -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoIssue(Model model) {
        model.updateFilteredIssueList(p -> false);

        assertTrue(model.getFilteredIssueList().isEmpty());
    }
}
