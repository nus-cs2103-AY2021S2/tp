package seedu.address.logic.commands;

import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetings.ListMeetingCommand;
import seedu.address.logic.commands.meetings.MeetingCommandTestUtil;
import seedu.address.logic.commands.persons.ListPersonCommand;
import seedu.address.logic.commands.persons.PersonCommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.note.NoteBook;
import seedu.address.model.person.AddressBook;

public class ListAllCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(),
                new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(),
                new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showMeetingAtIndex(model, INDEX_FIRST);
        showPersonAtIndex(model, INDEX_FIRST);
        MeetingCommandTestUtil.assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
