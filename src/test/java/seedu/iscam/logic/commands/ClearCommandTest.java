package seedu.iscam.logic.commands;

import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.testutil.TypicalClients.getTypicalLocationBook;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.UserPrefs;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalLocationBook(), getTypicalMeetingBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalLocationBook(), getTypicalMeetingBook(), new UserPrefs());
        expectedModel.setClientBook(new ClientBook());
        expectedModel.setMeetingBook(new MeetingBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
