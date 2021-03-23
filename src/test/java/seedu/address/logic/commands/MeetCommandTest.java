package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MeetCommand.IGNORE_CLASHES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MeetCommandTest {

    private static final String MEETING_PLACE = "KENT RIDGE MRT";
    private static final String MEETING_DATE = "15/06/2021";
    private static final String MEETING_TIME = "15:00";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person personToMeet = new PersonBuilder().build();
        Person meetPerson = MeetCommand.createMeeting(personToMeet, MEETING_PLACE, MEETING_DATE, MEETING_TIME);
        MeetCommand meetCommand = new MeetCommand(INDEX_FIRST_PERSON, MEETING_PLACE,
                MEETING_DATE, MEETING_TIME, IGNORE_CLASHES);

        String expectedMessage = String.format(MeetCommand.MESSAGE_MEET_PERSON_SUCCESS,
                meetPerson.getMeeting().get(0));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), meetPerson);

        assertCommandSuccess(meetCommand, model, expectedMessage, expectedModel);
    }
}
