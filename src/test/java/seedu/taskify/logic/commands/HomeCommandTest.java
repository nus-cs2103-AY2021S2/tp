package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.taskify.model.Model;
import seedu.taskify.model.ModelManager;
import seedu.taskify.model.UserPrefs;
import seedu.taskify.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for HomeCommand.
 */
public class HomeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void sameTaskList() {
        ObservableList<Task> firstTaskList = model.getFilteredTaskList();
        ObservableList<Task> secondTaskList = expectedModel.getFilteredTaskList();
        assert firstTaskList.equals(secondTaskList) == true;
    }

    @Test
    public void sameTask() {
        Task firstTaskInModel = model.getFilteredTaskList().get(0);
        Task firstTaskInExpectedModel = expectedModel.getFilteredTaskList().get(0);
        assert firstTaskInModel.equals(firstTaskInExpectedModel) == true;
    }

    @Test
    public void equals() {
        CommandResult.setHomeTab();
        final HomeCommand standardCommand = new HomeCommand();


        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
    }
}
