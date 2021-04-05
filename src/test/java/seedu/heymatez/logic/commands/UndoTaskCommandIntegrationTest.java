package seedu.heymatez.logic.commands;

import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
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
 * Contains integration tests (interaction with the Model) for {@code UndoTaskCommand}.
 */
public class UndoTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
    }

    @Test
    public void execute_undoTask_success() {
        Task validTask = model.getHeyMatez().getTaskList().get(0);
        Task undoTask = new TaskBuilder(validTask).withTaskStatus(TaskBuilder.COMPLETED_STATUS).build();

        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        model.setTask(validTask, undoTask);

        assertCommandSuccess(new UndoTaskCommand(INDEX_THIRD_TASK), model,
                UndoTaskCommand.MESSAGE_UNDO_TASK_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undo_throwsIndexNotValid() {
        Index index = Index.fromOneBased(model.getHeyMatez().getTaskList().size() + 1);
        assertCommandFailure(new UndoTaskCommand(index), model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_undo_throwsTaskIsAlreadyCompleted() {
        Index index = Index.fromOneBased(1);
        assertCommandFailure(new UndoTaskCommand(index), model,
                UndoTaskCommand.MESSAGE_TASK_ALREADY_MARKED_UNCOMPLETED);
    }
}
