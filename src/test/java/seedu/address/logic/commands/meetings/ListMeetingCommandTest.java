package seedu.address.logic.commands.meetings;

import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.showMeetingAtIndex;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.note.NoteBook;
import seedu.address.model.person.AddressBook;

public class ListMeetingCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), getTypicalMeetingBook(),
                        new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
        expectedModel = new ModelManager(new AddressBook(), getTypicalMeetingBook(),
                new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListMeetingCommand(), model, ListMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showMeetingAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListMeetingCommand(), model, ListMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
