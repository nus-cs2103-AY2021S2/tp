package seedu.address.logic.commands.meetings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.MEETING4;
import static seedu.address.testutil.TypicalMeetings.MEETING5;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingSortDirection;
import seedu.address.model.meeting.MeetingSortOption;
import seedu.address.model.note.NoteBook;
import seedu.address.model.person.AddressBook;

class SortMeetingCommandTest {

    private Model model = new ModelManager(new AddressBook(), getTypicalMeetingBook(), new NoteBook(),
            new UserPrefs(), new PersonMeetingConnection());

    @Test
    void executeStart() throws CommandException {
        SortMeetingCommand command = new SortMeetingCommand(MeetingSortOption.START,
                MeetingSortDirection.ASC);
        CommandResult results = command.execute(model);
        ObservableList<Meeting> filteredList = model.getFilteredMeetingList();
        assertEquals(MEETING4, filteredList.get(0));
        assertEquals(new CommandResult("Sorted meetings"), results);
    }
    @Test
    void executeDesc() throws CommandException {
        SortMeetingCommand command = new SortMeetingCommand(MeetingSortOption.DESCRIPTION,
                MeetingSortDirection.DESC);
        CommandResult results = command.execute(model);
        ObservableList<Meeting> filteredList = model.getFilteredMeetingList();
        assertEquals(MEETING4, filteredList.get(0));
        assertEquals(new CommandResult("Sorted meetings"), results);
    }
    @Test
    void executeEnd() throws CommandException {
        SortMeetingCommand command = new SortMeetingCommand(MeetingSortOption.END,
                MeetingSortDirection.ASC);
        CommandResult results = command.execute(model);
        ObservableList<Meeting> filteredList = model.getFilteredMeetingList();
        assertEquals(MEETING4, filteredList.get(0));
        assertEquals(new CommandResult("Sorted meetings"), results);
    }
    @Test
    void executeName() throws CommandException {
        SortMeetingCommand command = new SortMeetingCommand(MeetingSortOption.NAME,
                MeetingSortDirection.ASC);
        CommandResult results = command.execute(model);
        ObservableList<Meeting> filteredList = model.getFilteredMeetingList();
        assertEquals(MEETING5, filteredList.get(0));
        assertEquals(new CommandResult("Sorted meetings"), results);
    }
    @Test
    void executePriority() throws CommandException {
        SortMeetingCommand command = new SortMeetingCommand(MeetingSortOption.PRIORITY,
                MeetingSortDirection.ASC);
        CommandResult results = command.execute(model);
        ObservableList<Meeting> filteredList = model.getFilteredMeetingList();
        assertEquals(MEETING1, filteredList.get(0));
        assertEquals(new CommandResult("Sorted meetings"), results);
    }

}
