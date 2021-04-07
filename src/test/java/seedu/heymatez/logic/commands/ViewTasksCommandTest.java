package seedu.heymatez.logic.commands;

import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_TASK_LIST;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.testutil.TypicalTasks;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewTasksCommand}.
 */
public class ViewTasksCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalTasks.getTypicalHeyMatez(), new UserPrefs());
        expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewTasksCommand(), model, ViewTasksCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ViewTasksCommand(), model, ViewTasksCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyFilteredList_success() {
        HeyMatez heyMatez = new HeyMatez();
        model = new ModelManager(heyMatez, new UserPrefs());
        expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        assertCommandSuccess(new ViewTasksCommand(), model, MESSAGE_EMPTY_TASK_LIST, expectedModel);
    }
}
