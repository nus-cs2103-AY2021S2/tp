package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MeetCommand.CHECK_CLASHES;
import static seedu.address.logic.commands.MeetCommand.DELETE_MEETING;
import static seedu.address.logic.commands.MeetCommand.IGNORE_CLASHES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class MeetCommandTest {

    private static final String MEETING_PLACE = "KENT RIDGE MRT";
    private static final String MEETING_DATE = "15:06:2021";
    private static final String MEETING_TIME = "15:00";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_checkCreateMeeting_success() {
        Person personToMeet = model.getFilteredPersonList().get(0);
        Person meetPerson = MeetCommand.createMeeting(personToMeet, MEETING_PLACE, MEETING_DATE, MEETING_TIME);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, CHECK_CLASHES,
                MEETING_PLACE, MEETING_DATE, MEETING_TIME);

        String expectedMessage = String.format(MeetCommand.MESSAGE_MEET_PERSON_SUCCESS,
                meetPerson.getMeeting().get(0));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), meetPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_ignoreCreateMeeting_success() {
        Person personToMeet = model.getFilteredPersonList().get(0);
        Person meetPerson = MeetCommand.createMeeting(personToMeet, MEETING_PLACE, MEETING_DATE, MEETING_TIME);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, IGNORE_CLASHES,
                MEETING_PLACE, MEETING_DATE, MEETING_TIME);

        String expectedMessage = String.format(MeetCommand.MESSAGE_MEET_PERSON_SUCCESS,
                meetPerson.getMeeting().get(0));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), meetPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMeeting_success() {
        Person personToMeet = model.getFilteredPersonList().get(0);
        Person meetPerson = MeetCommand.createMeeting(personToMeet, MEETING_PLACE, MEETING_DATE, MEETING_TIME);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, DELETE_MEETING,
                MEETING_PLACE, MEETING_DATE, MEETING_TIME);

        String expectedMessage = MeetCommand.MESSAGE_DELETE_MEETING;

        try {
            meetCommand.execute(model);
        } catch (CommandException ex) {
            assert true;
        }

        Person noMeetingPerson = MeetCommand.deleteMeeting(meetPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), noMeetingPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }
}
