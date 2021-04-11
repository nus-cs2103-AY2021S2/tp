package seedu.address.logic.commands.meetings;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.assertCommandSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

class FindMeetingCommandTest {

    private Model model = new ModelManager(getTypicalMeetingBook(), new UserPrefs());

    @Test
    void execute() throws CommandException {
        model.addPerson(ALICE);
        FindMeetingCommand command = new FindMeetingCommand(meeting -> true
                , Set.of(Index.fromOneBased(1)));
        CommandResult result = command.execute(model);
        ObservableList<Meeting> list = model.getFilteredMeetingList();
        assertTrue(list.isEmpty());
        assertCommandSuccess(command,model,String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,0),model);
    }

    @Test
    void executeBad() {
        model.addPerson(ALICE);
        FindMeetingCommand command = new FindMeetingCommand(meeting -> true
                , Set.of(Index.fromOneBased(13)));
        assertCommandFailure(command,model, Messages.MESSAGE_INVALID_PERSONS_DISPLAYED_INDEX);
    }


}
