package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.assertMeetingCommandFailure;
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
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.meeting.CompletionStatus;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.testutil.MeetingBuilder;

public class RelocateMeetingCommandTest {
    private Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_locationFieldSpecifiedUnfilteredList_success() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(firstMeeting);
        Meeting relocatedMeeting = meetingInList.withLocation(VALID_LOCATION_DAN).build();

        RelocateMeetingCommand relocateMeetingCommand = new RelocateMeetingCommand(INDEX_FIRST_ITEM,
                new Location(VALID_LOCATION_DAN));

        String expectedMessage = String.format(RelocateMeetingCommand.MESSAGE_SUCCESS, relocatedMeeting);

        Model expectedModel = new ModelManager(new ClientBook(model.getClientBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), relocatedMeeting);

        assertCommandSuccess(relocateMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);

        Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        Meeting relocatedMeeting = new MeetingBuilder(meetingInFilteredList).withLocation(VALID_LOCATION_DAN).build();
        RelocateMeetingCommand relocateMeetingCommand = new RelocateMeetingCommand(INDEX_FIRST_ITEM,
                new Location(VALID_LOCATION_DAN));

        String expectedMessage = String.format(RelocateMeetingCommand.MESSAGE_SUCCESS, relocatedMeeting);

        Model expectedModel = new ModelManager(new ClientBook(model.getClientBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), relocatedMeeting);

        assertCommandSuccess(relocateMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        RelocateMeetingCommand relocateMeetingCommand = new RelocateMeetingCommand(INDEX_SECOND_ITEM,
                firstMeeting.getLocation());

        assertMeetingCommandFailure(relocateMeetingCommand, model, RelocateMeetingCommand.MESSAGE_DUPLICATE_LOCATION);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);

        // relocate meeting in filtered list into a duplicate in iScam book
        Meeting meetingInList = model.getMeetingBook().getMeetingList().get(INDEX_SECOND_ITEM.getZeroBased());
        RelocateMeetingCommand relocateMeetingCommand = new RelocateMeetingCommand(INDEX_FIRST_ITEM,
                meetingInList.getLocation());

        assertMeetingCommandFailure(relocateMeetingCommand, model, RelocateMeetingCommand.MESSAGE_DUPLICATE_LOCATION);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        RelocateMeetingCommand relocateMeetingCommand = new RelocateMeetingCommand(outOfBoundIndex,
                new Location(VALID_LOCATION_DAN));

        assertMeetingCommandFailure(relocateMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of iscam book
     */
    @Test
    public void execute_invalidMeetingIndexFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of iscam book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMeetingBook().getMeetingList().size());

        RelocateMeetingCommand relocateMeetingCommand = new RelocateMeetingCommand(outOfBoundIndex,
                new Location(VALID_LOCATION_DAN));

        assertMeetingCommandFailure(relocateMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_relocateAlreadyCompletedMeeting_failure() {
        Index lastIndex = Index.fromOneBased(model.getFilteredMeetingList().size() - 1);
        Meeting lastMeeting = model.getFilteredMeetingList().get(lastIndex.getZeroBased());

        // Ensures that the lastMeeting is completed
        assertEquals(lastMeeting.getStatus(), new CompletionStatus("complete"));

        RelocateMeetingCommand relocateMeetingCommand = new RelocateMeetingCommand(lastIndex,
                new Location(VALID_LOCATION_DAN));

        assertMeetingCommandFailure(relocateMeetingCommand, model, RelocateMeetingCommand.MESSAGE_ALREADY_COMPLETE);
    }

    @Test
    public void equals() {
        Location locationCleo = new Location(VALID_LOCATION_CLEO);
        Location locationDan = new Location(VALID_LOCATION_DAN);
        RelocateMeetingCommand relocateFirstCommand = new RelocateMeetingCommand(INDEX_FIRST_ITEM, locationCleo);
        RelocateMeetingCommand relocateSecondCommand = new RelocateMeetingCommand(INDEX_SECOND_ITEM, locationDan);

        // same object -> returns true
        assertTrue(relocateFirstCommand.equals(relocateFirstCommand));

        // same values -> returns true
        RelocateMeetingCommand relocateFirstCommandCopy = new RelocateMeetingCommand(INDEX_FIRST_ITEM, locationCleo);
        assertTrue(relocateFirstCommand.equals(relocateFirstCommandCopy));

        // different types -> returns false
        assertFalse(relocateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(relocateFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(relocateFirstCommand.equals(relocateSecondCommand));
    }
}
