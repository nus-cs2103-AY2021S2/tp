package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
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

class DoneMeetingCommandTest {
    private Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToComplete = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        Meeting completedMeeting = completeMeeting(meetingToComplete);
        DoneMeetingCommand doneMeetingCommand = new DoneMeetingCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DoneMeetingCommand.MESSAGE_SUCCESS, completedMeeting);

        ModelManager expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.setMeeting(meetingToComplete, completedMeeting);

        assertCommandSuccess(doneMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        DoneMeetingCommand doneMeetingCommand = new DoneMeetingCommand(outOfBoundIndex);

        assertCommandFailure(doneMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);

        Meeting meetingToComplete = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        Meeting completedMeeting = completeMeeting(meetingToComplete);
        DoneMeetingCommand doneMeetingCommand = new DoneMeetingCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DoneMeetingCommand.MESSAGE_SUCCESS, completedMeeting);

        Model expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.setMeeting(meetingToComplete, completedMeeting);

        assertCommandSuccess(doneMeetingCommand, model, expectedMessage, expectedModel);
    }

    //TODO
    @Test
    public void execute_alreadyCompleted_throwsCommandException() {
        Meeting meetingToComplete = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        Meeting completedMeeting = completeMeeting(meetingToComplete);

        ModelManager expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.setMeeting(meetingToComplete, completedMeeting);

        showSpecificMeeting(expectedModel, completedMeeting);
        DoneMeetingCommand doneMeetingCommand = new DoneMeetingCommand(INDEX_FIRST_ITEM);

        assertCommandFailure(doneMeetingCommand, expectedModel, DoneMeetingCommand.MESSAGE_ALREADY_COMPLETE);
    }

    @Test
    public void equals() {
        DoneMeetingCommand doneFirstCommand = new DoneMeetingCommand(INDEX_FIRST_ITEM);
        DoneMeetingCommand doneSecondCommand = new DoneMeetingCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneMeetingCommand doneFirstCommandCopy = new DoneMeetingCommand(INDEX_FIRST_ITEM);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }

    public static Meeting completeMeeting(Meeting meeting) {
        return new Meeting(meeting.getClientName(), meeting.getDateTime(), meeting.getLocation(),
                meeting.getDescription(), meeting.getTags(), meeting.getStatus().complete());
    }

    public static void showSpecificMeeting(Model model, Meeting meeting) {
        assertTrue(model.hasMeeting(meeting));

        model.updateFilteredMeetingList(meeting::equals);
    }

    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        model.updateFilteredMeetingList(meeting::equals);
    }
}
