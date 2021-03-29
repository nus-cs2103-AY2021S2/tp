package seedu.plan.logic.commands;

import static seedu.plan.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plan.testutil.TypicalPlans.getTypicalModulePlanner;

import org.junit.jupiter.api.Test;

import seedu.plan.model.Model;
import seedu.plan.model.ModelManager;
import seedu.plan.model.ModulePlanner;
import seedu.plan.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        expectedModel.setPlans(new ModulePlanner());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
