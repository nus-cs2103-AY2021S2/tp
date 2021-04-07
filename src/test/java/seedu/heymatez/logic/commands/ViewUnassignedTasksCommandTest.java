package seedu.heymatez.logic.commands;

import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.testutil.TaskBuilder;
import seedu.heymatez.testutil.TypicalTasks;


/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewUnassignedTasksCommand}.
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

    @Test
    public void execute_emptyFilteredList_showsNoTask() {
        HeyMatez hm = new HeyMatez();
        assertCommandSuccess(new ViewUnassignedTasksCommand(), new ModelManager(hm, new UserPrefs()),
                ViewUnassignedTasksCommand.MESSAGE_NO_UNASSIGNED_TASKS,
                new ModelManager(hm, new UserPrefs()));
    }

    @Test
    public void executed_noUnassignedTaskFilteredList_showsNoTask() {
        Task homework = new TaskBuilder().withTitle("Homework").withDescription("do CS2103tp")
                .withDeadline("2021-02-04").withTaskStatus("completed").withPriority("unassigned")
                .withAssignees("Rachel").build();
        HeyMatez hm = new HeyMatez();
        hm.addTask(homework);
        Model newModel = new ModelManager(hm, new UserPrefs());
        Model myExpectedModel = new ModelManager(hm, new UserPrefs());
        assertCommandSuccess(new ViewUnassignedTasksCommand(), newModel,
                ViewUnassignedTasksCommand.MESSAGE_NO_UNASSIGNED_TASKS,
                myExpectedModel);
    }
}
