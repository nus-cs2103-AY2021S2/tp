package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskify.testutil.TypicalTasks.getTypicalTaskify;

import org.junit.jupiter.api.Test;

import seedu.taskify.model.Model;
import seedu.taskify.model.ModelManager;
import seedu.taskify.model.UserPrefs;

class SortCommandTest {
    private Model model = new ModelManager(getTypicalTaskify(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskify(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand testSortCommand = new SortCommand();

        // same object -> returns true
        assertTrue(testSortCommand.equals(testSortCommand));

        // same values -> returns true
        SortCommand secondTestSortCommand = new SortCommand();
        assertTrue(testSortCommand.equals(secondTestSortCommand));

        // different types -> returns false
        assertFalse(testSortCommand.equals(1));

        // null -> returns false
        assertFalse(testSortCommand.equals(null));
    }

    @Test
    public void execute_sortedTasksFound() {
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        SortCommand command = new SortCommand();
        expectedModel.sortTask();
        expectedModel.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
