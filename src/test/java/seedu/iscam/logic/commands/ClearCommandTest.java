package seedu.iscam.logic.commands;

import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;

public class ClearCommandTest {

    @Test
    public void execute_emptyBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyBook_success() {
        Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());
        expectedModel.setClientBook(new ClientBook());
        expectedModel.setMeetingBook(new MeetingBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
