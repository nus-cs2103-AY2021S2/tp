package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.clearcommand.ClearCommand;
import seedu.address.logic.commands.clearcommand.ClearModulesCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RemindMe;
import seedu.address.model.UserPrefs;

public class ClearModulesCommandTest {

    @Test
    public void execute_emptyRemindMe_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearModulesCommand(), model, ClearModulesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRemindMe_success() {
        Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalRemindMe(), new UserPrefs());
        expectedModel.setRemindMe(new RemindMe());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
