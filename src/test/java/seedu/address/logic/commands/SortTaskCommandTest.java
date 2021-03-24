package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalSochedule;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskComparator;

public class SortTaskCommandTest {
    private Model model = new ModelManager(getTypicalSochedule(), new UserPrefs());

    @Test
    public void execute_valid_success() {
        for (String comparingVar : TaskComparator.getAcceptedVar()) {
            SortTaskCommand sortTaskCommand = new SortTaskCommand(comparingVar);

            String expectedMessage = String.format(SortTaskCommand.MESSAGE_SORT_TASK_SUCCESS);

            ModelManager expectedModel = new ModelManager(model.getSochedule(), new UserPrefs());
            expectedModel.sortTasks(comparingVar);

            assertCommandSuccess(sortTaskCommand, model, expectedMessage, expectedModel);
        }
    }

    // invalid inputs already handled on parsing sort_task command
}
