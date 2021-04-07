package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.logic.commands.CommandTestUtil.DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DATETIME_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.iscam.logic.commands.CommandTestUtil.assertMeetingCommandFailure;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.testutil.MeetingBuilder;
import seedu.iscam.testutil.EditMeetingDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingCommand.
 */
public class EditMeetingCommandTest {

    private Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editCommand = new EditMeetingCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new ClientBook(model.getClientBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexThirdMeeting = Index.fromOneBased(3);
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexThirdMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withName(VALID_CLIENT_NAME_DAN).withDateTime(VALID_DATETIME_DAN)
                .withTags(VALID_TAG_URGENT).build();

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withClientName(VALID_CLIENT_NAME_DAN)
                .withDateTime(VALID_DATETIME_DAN).withTags(VALID_TAG_URGENT).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexThirdMeeting, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new ClientBook(model.getClientBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_ITEM, new EditMeetingDescriptor());
        assertMeetingCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_NO_CHANGES);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);

        Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withName(VALID_CLIENT_NAME_CLEO).build();
        EditMeetingCommand editCommand = new EditMeetingCommand(INDEX_FIRST_ITEM,
                new EditMeetingDescriptorBuilder().withClientName(VALID_CLIENT_NAME_CLEO).build());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new ClientBook(model.getClientBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingCommand editCommand = new EditMeetingCommand(INDEX_SECOND_ITEM, descriptor);

        assertMeetingCommandFailure(editCommand, model, EditMeetingCommand.MESSAGE_CONFLICT);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_ITEM);

        // edit meeting in filtered list into a duplicate in iScam book
        Meeting meetingInList = model.getMeetingBook().getMeetingList().get(INDEX_SECOND_ITEM.getZeroBased());
        EditMeetingCommand editCommand = new EditMeetingCommand(INDEX_FIRST_ITEM,
                new EditMeetingDescriptorBuilder(meetingInList).build());

        assertMeetingCommandFailure(editCommand, model, EditMeetingCommand.MESSAGE_CONFLICT);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withClientName(VALID_CLIENT_NAME_CLEO).build();
        EditMeetingCommand editCommand = new EditMeetingCommand(outOfBoundIndex, descriptor);

        assertMeetingCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
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

        EditMeetingCommand editCommand = new EditMeetingCommand(outOfBoundIndex,
                new EditMeetingDescriptorBuilder().withClientName(VALID_CLIENT_NAME_CLEO).build());

        assertMeetingCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMeetingCommand standardCommand = new EditMeetingCommand(INDEX_FIRST_ITEM, DESC_DAN);

        // same values -> returns true
        EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(DESC_DAN);
        EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_SECOND_ITEM, DESC_DAN)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_FIRST_ITEM, DESC_CLEO)));
    }

}
