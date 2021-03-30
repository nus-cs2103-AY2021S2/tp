package seedu.address.logic.commands;

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

public class MeetCommandTest {

    private static final String MEETING_DATE = "15.06.2021";
    private static final String MEETING_START = "15:00";
    private static final String MEETING_END = "18:00";
    private static final String MEETING_PLACE = "KENT RIDGE MRT";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addMeeting_success() {
        Person personToMeet = model.getFilteredPersonList().get(0);
        Meeting meeting = new Meeting(MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);
        Person meetPerson = MeetCommand.addMeeting(personToMeet, meeting);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, ADD_MEETING,
                MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);

        String expectedMessage = String.format(MeetCommand.MESSAGE_ADD_MEETING, meeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), meetPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMeeting_success() {
        Person personToMeet = model.getFilteredPersonList().get(0);
        Meeting meeting = new Meeting(MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);
        Person meetPerson = MeetCommand.addMeeting(personToMeet, meeting);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, DELETE_MEETING,
                MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE);

        String expectedMessage = String.format(MeetCommand.MESSAGE_DELETE_MEETING, meeting);

        try {
            meetCommand.execute(model);
        } catch (CommandException ex) {
            assert true;
        }

        Person noMeetingPerson = MeetCommand.deleteMeeting(meetPerson, meeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), noMeetingPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearMeeting_success() {
        Person personToMeet = model.getFilteredPersonList().get(0);
        Person meetPerson = MeetCommand.clearMeeting(personToMeet);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, CLEAR_MEETING,
                MEETING_EMPTY, MEETING_START, MEETING_END, MEETING_PLACE);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), meetPerson);

        assertCommandSuccess(meetCommand, model, MeetCommand.MESSAGE_CLEAR_MEETING, expectedModel);
    }
}
