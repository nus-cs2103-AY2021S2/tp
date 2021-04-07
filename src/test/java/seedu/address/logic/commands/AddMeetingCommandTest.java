package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DATE_BEFORE_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.MEETING_1;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

class AddMeetingCommandTest {

    private static final Meeting VALID_MEETING = MEETING_1;

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addMeetingUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMeetings(VALID_MEETING).build();

        AddMeetingCommand cmd = new AddMeetingCommand(INDEX_FIRST_PERSON, VALID_MEETING);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS, editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMeetingFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMeetings(VALID_MEETING).build();

        AddMeetingCommand cmd = new AddMeetingCommand(INDEX_FIRST_PERSON, VALID_MEETING);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS, editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddMeetingCommand cmd = new AddMeetingCommand(outOfBoundIndex, VALID_MEETING);

        assertCommandFailure(cmd, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddMeetingCommand meetingCommand = new AddMeetingCommand(outOfBoundIndex, VALID_MEETING);
        assertCommandFailure(meetingCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validMeetings_success() {
        // this person needs to match the person used in #testValidMeeting
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDate birthDate = firstPerson.getBirthday().getDate();

        // Acceptable range of values is from date of birthday to today (before the current time)
        testValidMeeting(new MeetingBuilder().withDate(birthDate).build());
        testValidMeeting(new MeetingBuilder().withDate(birthDate.plusDays(1)).build());
    }

    public void testValidMeeting(Meeting meeting) {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMeetings(meeting).build();

        AddMeetingCommand cmd = new AddMeetingCommand(INDEX_FIRST_PERSON, meeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS, editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);

        // reset model
        model.setPerson(editedPerson, firstPerson);
    }

    @Test
    public void execute_invalidMeetings_failure() {
        // this person needs to match the person used in #testInvalidMeetings
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDate birthDate = firstPerson.getBirthday().getDate();
        Meeting meetingBeforeBirthDate = new MeetingBuilder().withDate(birthDate.minusDays(1)).build();

        testInvalidMeeting(meetingBeforeBirthDate, String.format(
                MESSAGE_DATE_BEFORE_BIRTHDAY, DateUtil.toErrorMessage(meetingBeforeBirthDate.getDate())));
    }

    public void testInvalidMeeting(Meeting meeting, String errorMessage) {
        AddMeetingCommand cmd = new AddMeetingCommand(INDEX_FIRST_PERSON, meeting);
        assertCommandFailure(cmd, model, errorMessage);
    }
}
