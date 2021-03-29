package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalTasks;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewUnassignedTasksCommand.
 */
public class ViewUnassignedTasksCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalTasks.getTypicalHeyMatez(), new UserPrefs());
        expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewUnassignedTasksCommand(), model,
                ViewUnassignedTasksCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ViewUnassignedTasksCommand(), model,
                ViewUnassignedTasksCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
