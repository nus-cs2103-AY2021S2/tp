package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_UNDONE_TASKS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPlanner(), new UserPrefs());
        expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(true), model,
              ListCommand.MESSAGE_ALL_TASKS_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListCommand(true), model,
               ListCommand.MESSAGE_ALL_TASKS_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsUndoneTasks() {
        ListCommand command = new ListCommand(false);
        expectedModel.updateFilteredTaskList(PREDICATE_SHOW_UNDONE_TASKS);
        assertCommandSuccess(command, expectedModel,
                ListCommand.MESSAGE_UNDONE_TASKS_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ListCommand firstCommand = new ListCommand(false);
        ListCommand secondCommand = new ListCommand(true);

        // Same object
        assertTrue(firstCommand.equals(firstCommand));

        // not null
        assertFalse(firstCommand.equals(null));

        // Different type
        assertFalse(firstCommand.equals("testing"));

        // Different object
        assertFalse(firstCommand.equals(secondCommand));
    }
}
