package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.taskify.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskify.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.taskify.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.taskify.model.Model;
import seedu.taskify.model.ModelManager;
import seedu.taskify.model.Taskify;
import seedu.taskify.model.UserPrefs;
import seedu.taskify.model.task.Task;
import seedu.taskify.testutil.EditTaskDescriptorBuilder;
import seedu.taskify.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        CommandResult.setHomeTab();
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new Taskify(model.getAddressBook()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        CommandResult.setHomeTab();
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withName(VALID_NAME_BOB).withDescription(VALID_DESCRIPTION_BOB)
                                  .withTags(VALID_TAG_HUSBAND).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB)
                                                .withDescription(VALID_DESCRIPTION_BOB)
                                                .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new Taskify(model.getAddressBook()), new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        CommandResult.setHomeTab();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, new EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new Taskify(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandResult.setHomeTab();
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new Taskify(model.getAddressBook()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        CommandResult.setHomeTab();
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        CommandResult.setHomeTab();
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        // edit task in filtered list into a duplicate in Taskify
        Task taskInList = model.getAddressBook().getTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }


    @Test
    public void equals() {
        CommandResult.setHomeTab();
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_TASK, DESC_AMY);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TASK, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TASK, DESC_BOB)));
    }

}
