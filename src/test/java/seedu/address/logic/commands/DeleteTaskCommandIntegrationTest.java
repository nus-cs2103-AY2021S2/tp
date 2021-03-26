package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;


public class DeleteTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_deleteTask_success() {
        Task validTask = model.getAddressBook().getTaskList().get(0);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(validTask);

        Index index = Index.fromOneBased(1);
        assertCommandSuccess(new DeleteTaskCommand(index), model,
                String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, validTask), expectedModel);
    }

    @Test
    public void execute_deletePerson_throwsIndexNotValid() {
        Index index = Index.fromOneBased(model.getAddressBook().getTaskList().size() + 1);
        assertCommandFailure(new DeleteTaskCommand(index), model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

}
