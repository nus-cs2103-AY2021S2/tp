package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskify.logic.commands.CommandTestUtil.showTasksAtIndexes;
import static seedu.taskify.testutil.TypicalIndexes.INDEXES_FIRST_TO_THIRD_TASK;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.taskify.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.taskify.testutil.TypicalTasks.ALICE;
import static seedu.taskify.testutil.TypicalTasks.BENSON;
import static seedu.taskify.testutil.TypicalTasks.CARL;
import static seedu.taskify.testutil.TypicalTasks.ELLE;
import static seedu.taskify.testutil.TypicalTasks.getTypicalAddressBook;
import static seedu.taskify.testutil.TypicalTasks.getTypicalTasks;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taskify.commons.core.Messages;
import seedu.taskify.commons.core.index.Index;
import seedu.taskify.model.Model;
import seedu.taskify.model.ModelManager;
import seedu.taskify.model.UserPrefs;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.StatusType;
import seedu.taskify.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMultipleCommand}.
 */
public class DeleteMultipleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private static final String MESSAGE_DELETE_FIRST_TO_THIRD_TASK_SUCCESS =
            "Deleted Tasks: " + ALICE.toString() + BENSON.toString() + CARL.toString();
    private static final String MESSAGE_DELETE_COMPLETED_TASKS_SUCCESS =
            "Deleted Tasks: " + CARL.toString() + ELLE.toString();

    private List<Task> getTasksByIndexes(List<Index> indexes) {
        List<Task> requestedTasks = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++) {
            requestedTasks.add(model.getFilteredTaskList().get(i));
        }
        return requestedTasks;
    }

    private void deleteTasksFromModel(Model model, List<Task> tasksToDelete) {
        for (Task toDelete : tasksToDelete) {
            model.deleteTask(toDelete);
        }
    }


    @Test
    public void execute_validIndexUnfilteredList_success() {
        List<Task> tasksToDelete = getTasksByIndexes(INDEXES_FIRST_TO_THIRD_TASK);
        DeleteMultipleCommand deleteMulCommand = new DeleteMultipleCommand(INDEXES_FIRST_TO_THIRD_TASK);

        String expectedMessage = MESSAGE_DELETE_FIRST_TO_THIRD_TASK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        deleteTasksFromModel(expectedModel, tasksToDelete);

        assertCommandSuccess(deleteMulCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        List<Index> targetIndexes = new ArrayList<>();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        targetIndexes.add(INDEX_FIRST_TASK);
        targetIndexes.add(INDEX_SECOND_TASK);
        targetIndexes.add(outOfBoundIndex);

        DeleteMultipleCommand deleteMulCommand = new DeleteMultipleCommand(targetIndexes);

        assertCommandFailure(deleteMulCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTasksAtIndexes(model, INDEXES_FIRST_TO_THIRD_TASK);

        List<Task> tasksToDelete = getTasksByIndexes(INDEXES_FIRST_TO_THIRD_TASK);
        DeleteMultipleCommand deleteMulCommand = new DeleteMultipleCommand(INDEXES_FIRST_TO_THIRD_TASK);

        String expectedMessage = MESSAGE_DELETE_FIRST_TO_THIRD_TASK_SUCCESS;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        deleteTasksFromModel(expectedModel, tasksToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteMulCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTasksAtIndexes(model, INDEXES_FIRST_TO_THIRD_TASK);

        Index outOfBoundIndex = Index.fromOneBased(4);
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());
        List<Index> indexes = new ArrayList<>(INDEXES_FIRST_TO_THIRD_TASK);
        indexes.add(outOfBoundIndex);
        DeleteMultipleCommand deleteMulCommand = new DeleteMultipleCommand(indexes);

        assertCommandFailure(deleteMulCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }


    @Test
    public void execute_deleteByStatus_success() {
        List<Task> tasksToDelete = new ArrayList<>();
        tasksToDelete.add(CARL);
        tasksToDelete.add(ELLE);

        DeleteMultipleCommand deleteMulCommand = new DeleteMultipleCommand(new Status(StatusType.COMPLETED));

        String expectedMessage = MESSAGE_DELETE_COMPLETED_TASKS_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        deleteTasksFromModel(expectedModel, tasksToDelete);

        assertCommandSuccess(deleteMulCommand, model, expectedMessage, expectedModel);
    }



    @Test
    public void equals() {
        List<Index> firstList = new ArrayList<>();
        List<Index> secondList = new ArrayList<>();
        firstList.add(INDEX_FIRST_TASK);
        firstList.add(INDEX_SECOND_TASK);
        secondList.add(INDEX_FIRST_TASK);
        secondList.add(INDEX_THIRD_TASK);

        DeleteMultipleCommand firstCommand = new DeleteMultipleCommand(firstList);
        DeleteMultipleCommand secondCommand = new DeleteMultipleCommand(secondList);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeleteMultipleCommand secondCommandCopy = new DeleteMultipleCommand(secondList);
        assertTrue(secondCommand.equals(secondCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different task -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
