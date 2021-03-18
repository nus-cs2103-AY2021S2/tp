package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.clearcommand.ClearModuleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RemindMe;
import seedu.address.model.UserPrefs;

public class ClearModuleCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearModuleCommand(), model, ClearModuleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalRemindMe(), new UserPrefs());
        expectedModel.setRemindMe(new RemindMe());

        assertCommandSuccess(new ClearModuleCommand(), model, ClearModuleCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
