package seedu.address.logic.commands.meetings;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.persons.UnsortPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

class UnsortMeetingCommandTest {

    private Model model = new ModelManager(getTypicalMeetingBook(), new UserPrefs());

    @Test
    void execute() throws CommandException {
        UnsortMeetingCommand sortCommand = new UnsortMeetingCommand();
        CommandResult results = sortCommand.execute(model);
        ObservableList<Meeting> filteredList = model.getFilteredMeetingList();
        assertEquals(MEETING1,filteredList.get(0));
        assertEquals(new CommandResult("Unsorted the meeting list"), results);
    }
}
