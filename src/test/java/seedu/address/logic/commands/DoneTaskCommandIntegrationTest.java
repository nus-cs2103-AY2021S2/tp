package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class DoneTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_doneTask_success() {
        Task validTask = model.getAddressBook().getTaskList().get(0);
        Task doneTask = new TaskBuilder(validTask).withTaskStatus(TaskBuilder.COMPLETED_STATUS).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.setTask(validTask, doneTask);

        assertCommandSuccess(new DoneTaskCommand(INDEX_FIRST_TASK), model, DoneTaskCommand.MESSAGE_DONE_TASK_SUCCESS, expectedModel);
    }

    @Test
    public void execute_done_throwsIndexNotValid() {
        Index index = Index.fromOneBased(model.getAddressBook().getTaskList().size() + 1);
        assertCommandFailure(new DoneTaskCommand(index), model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_done_throwsTaskIsAlreadyCompleted() {
        Index index = Index.fromOneBased(3);
        assertCommandFailure(new DoneTaskCommand(INDEX_THIRD_TASK), model, DoneTaskCommand.MESSAGE_TASK_ALREADY_MARKED_DONE);
    }
}
