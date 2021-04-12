package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskDateComparator;


public class SortCommandTest {

    @Test
    public void execute_emptyPlanner_success() {
        Model model = new ModelManager();
        TaskDateComparator expectedPredicate = new TaskDateComparator();
        SortCommand sortCommand = new SortCommand(expectedPredicate, true);
        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_nonEmptyPlanner_success() {
        Model model = new ModelManager();
        TaskDateComparator expectedPredicate = new TaskDateComparator();
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());
        SortCommand sortCommand = new SortCommand(expectedPredicate, true);
        assertCommandSuccess(sortCommand, model, expectedMessage , expectedModel);
    }

}
