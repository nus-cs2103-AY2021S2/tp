package seedu.taskify.logic.commands;

import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskify.testutil.TypicalTasks.getTypicalTaskify;

import org.junit.jupiter.api.Test;

import seedu.taskify.model.Model;
import seedu.taskify.model.ModelManager;
import seedu.taskify.model.Taskify;
import seedu.taskify.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTaskify_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTaskify_success() {
        Model model = new ModelManager(getTypicalTaskify(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTaskify(), new UserPrefs());
        expectedModel.setTaskifyData(new Taskify());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
