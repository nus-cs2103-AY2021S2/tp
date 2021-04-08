package seedu.module.logic.commands;

import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.Test;

import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;

class RefreshCommandTest {

    @Test
    public void execute_emptyModuleBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new RefreshCommand(), model, RefreshCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyModuleBook_success() {
        Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModuleBook(), new UserPrefs());

        expectedModel.refreshTasks();

        assertCommandSuccess(new RefreshCommand(), model, RefreshCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
