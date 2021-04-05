package seedu.heymatez.logic.commands;

import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.heymatez.testutil.TypicalTasks.getTypicalHeyMatez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.core.Messages;
import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code DoneTaskCommand}.
 */
public class DoneTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
    }

    @Test
    public void execute_doneTask_success() {
        Task validTask = model.getHeyMatez().getTaskList().get(0);
        Task doneTask = new TaskBuilder(validTask).withTaskStatus(TaskBuilder.COMPLETED_STATUS).build();

        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        model.setTask(validTask, doneTask);

        assertCommandSuccess(new DoneTaskCommand(INDEX_FIRST_TASK), model, DoneTaskCommand.MESSAGE_DONE_TASK_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_done_throwsIndexNotValid() {
        Index index = Index.fromOneBased(model.getHeyMatez().getTaskList().size() + 1);
        assertCommandFailure(new DoneTaskCommand(index), model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_done_throwsTaskIsAlreadyCompleted() {
        Index index = Index.fromOneBased(3);
        assertCommandFailure(new DoneTaskCommand(INDEX_THIRD_TASK), model,
                DoneTaskCommand.MESSAGE_TASK_ALREADY_MARKED_DONE);
    }
}
