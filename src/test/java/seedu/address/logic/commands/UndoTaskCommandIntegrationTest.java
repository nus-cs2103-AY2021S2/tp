package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalHeyMatez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

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
