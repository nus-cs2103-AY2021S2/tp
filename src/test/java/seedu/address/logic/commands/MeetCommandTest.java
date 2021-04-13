package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MeetCommand.ADD_MEETING;
import static seedu.address.logic.commands.MeetCommand.CLEAR_MEETING;
import static seedu.address.logic.commands.MeetCommand.DELETE_MEETING;
import static seedu.address.logic.commands.MeetCommand.MEETING_EMPTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.shortcut.ShortcutLibrary;
import seedu.address.storage.Authentication;

public class MeetCommandTest {

    private static final String MEETING_DATE = "15.06.2021";
    private static final String MEETING_START = "15:00";
    private static final String MEETING_END = "18:00";
    private static final String MEETING_PLACE = "KENT RIDGE MRT";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Authentication(),
            new ShortcutLibrary());

    @Test
    public void execute_addMeetingNoClashes_success() {
        Person person = model.getFilteredPersonList().get(0);
        Meeting meeting = new Meeting(MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);
        Person newPerson = MeetCommand.addMeeting(person, meeting);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, ADD_MEETING,
                MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);

        String expectedMessage = String.format(MeetCommand.MESSAGE_ADD_MEETING, meeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new Authentication(), new ShortcutLibrary());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), newPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMeetingHasClashes_failure() {
        Person person = model.getFilteredPersonList().get(0);
        Meeting clashedMeeting = person.getMeetings().get(0);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, ADD_MEETING,
                clashedMeeting.date, MEETING_START, MEETING_END, MEETING_PLACE);

        String clash = person.getName() + "  " + clashedMeeting.toString();
        String expectedMessage = String.format(MeetCommand.MESSAGE_CLASHING_MEETING, clash);

        assertCommandFailure(meetCommand, model, expectedMessage);
    }

    @Test
    public void execute_deleteMeeting_success() {
        Person person = model.getFilteredPersonList().get(0);
        Meeting meeting = person.getMeetings().get(0);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, DELETE_MEETING,
                meeting.date, meeting.start, meeting.end, meeting.place);

        String expectedMessage = String.format(MeetCommand.MESSAGE_DELETE_MEETING, meeting);
        Person newPerson = person;

        try {
            newPerson = MeetCommand.deleteMeeting(person, meeting);
        } catch (CommandException ex) {
            assert true;
        }

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new Authentication() , new ShortcutLibrary());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), newPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMeeting_failure() {
        Meeting meeting = new Meeting(MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, DELETE_MEETING,
                MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);

        String expectedMessage = String.format(MeetCommand.MESSAGE_DELETE_FAIL, meeting);

        assertCommandFailure(meetCommand, model, expectedMessage);
    }

    @Test
    public void execute_clearMeeting_success() {
        Person person = model.getFilteredPersonList().get(0);
        Person newPerson = MeetCommand.clearMeeting(person);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, CLEAR_MEETING,
                MEETING_EMPTY, MEETING_EMPTY, MEETING_EMPTY, MEETING_EMPTY);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new Authentication(), new ShortcutLibrary());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), newPerson);

        assertCommandSuccess(meetCommand, model, MeetCommand.MESSAGE_CLEAR_MEETING, expectedModel);
    }
}
