package seedu.iScam.logic.commands;

import static seedu.iScam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iScam.testutil.TypicalClients.getTypicalLocationBook;

import org.junit.jupiter.api.Test;

import seedu.iScam.model.ClientBook;
import seedu.iScam.model.Model;
import seedu.iScam.model.ModelManager;
import seedu.iScam.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalLocationBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalLocationBook(), new UserPrefs());
        expectedModel.setClientBook(new ClientBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
