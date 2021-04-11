package seedu.address.logic.commands.meetings;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_NAME_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_PRIORITY_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_START_MEETING3;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_TERMINATE_MEETING3;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetings.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.note.NoteBook;
import seedu.address.model.person.AddressBook;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;

public class EditMeetingCommandTest {

    private Model model =
            new ModelManager(new AddressBook(), getTypicalMeetingBook(),
                    new NoteBook(), new UserPrefs(), new PersonMeetingConnection());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(), getTypicalMeetingBook(),
                new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withName(VALID_NAME_MEETING1)
                .withStart(VALID_START_MEETING3)
                .withTerminate(VALID_TERMINATE_MEETING3)
                .withPriority(VALID_PRIORITY_MEETING1)
                .build();

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withName(VALID_NAME_MEETING1)
                .withStart(VALID_START_MEETING3)
                .withTerminate(VALID_TERMINATE_MEETING3)
                .withPriority(VALID_PRIORITY_MEETING1)
                .build();

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new NoteBook(model.getNoteBook()),
                new UserPrefs(),
                model.getPersonMeetingConnection());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST, new EditMeetingDescriptor());
        Meeting editedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager( new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new NoteBook(model.getNoteBook()),
                new UserPrefs(),
                model.getPersonMeetingConnection()
        );

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST);

        Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withName(VALID_NAME_MEETING1).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST,
                new EditMeetingDescriptorBuilder().withName(VALID_NAME_MEETING1).build());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(), getTypicalMeetingBook(),
                new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST);

        // edit meeting in filtered list into a duplicate in meeting book
        Meeting meetingInList = model.getMeetingBook().getMeetingList().get(INDEX_SECOND.getZeroBased());
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST,
                new EditMeetingDescriptorBuilder(meetingInList).build());

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_MEETING1).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of meeting book
     */
    @Test
    public void execute_invalidMeetingIndexFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of meeting book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMeetingBook().getMeetingList().size());

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex,
                new EditMeetingDescriptorBuilder().withName(VALID_NAME_MEETING1).build());

        MeetingCommandTestUtil.assertCommandFailure(editMeetingCommand, model,
                Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }
}
