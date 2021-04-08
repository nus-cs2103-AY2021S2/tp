package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.assertClientCommandFailure;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.user.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMeetingCommand}.
 */
public class DeleteMeetingCommandTest {

    private Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteMeetingCommand deleteCommand = new DeleteMeetingCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        ModelManager expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        DeleteMeetingCommand deleteCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertClientCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);

        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteMeetingCommand deleteCommand = new DeleteMeetingCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        Model expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);
        showNoMeeting(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);

        Index outOfBoundIndex = INDEX_SECOND_ITEM;

        // Ensures that outOfBoundIndex is still in bounds of iScam book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMeetingBook().getMeetingList().size());

        DeleteMeetingCommand deleteCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertClientCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMeetingCommand deleteFirstCommand = new DeleteMeetingCommand(INDEX_FIRST_ITEM);
        DeleteMeetingCommand deleteSecondCommand = new DeleteMeetingCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMeetingCommand deleteFirstCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_ITEM);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no meeting.
     */
    private void showNoMeeting(Model model) {
        model.updateFilteredMeetingList(p -> false);

        assertTrue(model.getFilteredMeetingList().isEmpty());
    }
}
