package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModuleBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyModuleBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyModuleBook_success() {
        Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModuleBook(), new UserPrefs());
        expectedModel.setModuleBook(new ModuleBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
