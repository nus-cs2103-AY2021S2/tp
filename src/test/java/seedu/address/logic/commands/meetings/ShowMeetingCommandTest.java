package seedu.address.logic.commands.meetings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.MEETING2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonInMeetingPredicate;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.note.NoteBook;
import seedu.address.model.person.AddressBook;

public class ShowMeetingCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new MeetingBook(),
                new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
        expectedModel = new ModelManager(new AddressBook(), new MeetingBook(),
                new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
        model.addPerson(ALICE);
        expectedModel.addPerson(ALICE);
        model.addPerson(CARL);
        expectedModel.addPerson(CARL);
        model.addPerson(ELLE);
        expectedModel.addPerson(ELLE);
        model.addPerson(FIONA);
        expectedModel.addPerson(FIONA);
    }

    @Test
    public void execute_onePersonFound_multipleMeetings(){
        this.setUp();
        Meeting meeting1 = MEETING1;
        PersonMeetingConnection personMeetingConnection = new PersonMeetingConnection();
        personMeetingConnection.addPersonMeetingConnection(FIONA, MEETING1);
        meeting1.setPersonMeetingConnection(personMeetingConnection);
        model.addMeeting(meeting1);
        expectedModel.addMeeting(meeting1);
        model.addMeeting(MEETING2);
        expectedModel.addMeeting(MEETING2);

        List<Meeting> lastShownList = model.getFilteredMeetingList();

        Meeting meeting = lastShownList.get(0);
        ShowMeetingCommand command = new ShowMeetingCommand(Index.fromOneBased(1));
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        PersonInMeetingPredicate predicate = new PersonInMeetingPredicate(meeting);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPersonList());

        model.deleteMeeting(meeting1);
        model.deleteMeeting(MEETING2);
        expectedModel.deleteMeeting(meeting1);
        expectedModel.deleteMeeting(MEETING2);
    }

    @Test
    public void execute_multiplePersonsFound_multipleMeetings() {
        this.setUp();
        model.addMeeting(MEETING1);
        expectedModel.addMeeting(MEETING1);

        Meeting meeting2 = MEETING2;
        PersonMeetingConnection personMeetingConnection = new PersonMeetingConnection();
        personMeetingConnection.addPersonMeetingConnection(ALICE, MEETING2);
        personMeetingConnection.addPersonMeetingConnection(ELLE, MEETING2);
        personMeetingConnection.addPersonMeetingConnection(FIONA, MEETING2);
        meeting2.setPersonMeetingConnection(personMeetingConnection);
        model.addMeeting(meeting2);
        expectedModel.addMeeting(meeting2);

        List<Meeting> lastShownList = model.getFilteredMeetingList();

        Meeting meeting = lastShownList.get(1);
        ShowMeetingCommand command = new ShowMeetingCommand(Index.fromOneBased(2));
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);

        PersonInMeetingPredicate predicate = new PersonInMeetingPredicate(meeting);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredPersonList());
    }

}
