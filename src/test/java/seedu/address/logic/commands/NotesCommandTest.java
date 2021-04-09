package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskTracker;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Task;
import seedu.address.testutil.TaskBuilder;

public class NotesCommandTest {

    private static final String NOTES_STUB = "Some notes";

    private Model model = new ModelManager(getTypicalTaskTracker(), new UserPrefs());

    @Test
    public void equals() {
        final NotesCommand standardCommand = new NotesCommand(INDEX_FIRST_TASK, new Notes(VALID_REMARK_AMY));

        // same values -> returns true
        NotesCommand commandWithSameValues = new NotesCommand(INDEX_FIRST_TASK, new Notes(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new NotesCommand(INDEX_SECOND_TASK, new Notes(VALID_REMARK_AMY))));

        // different notes -> returns false
        assertFalse(standardCommand.equals(new NotesCommand(INDEX_FIRST_TASK, new Notes(VALID_REMARK_BOB))));
    }

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(firstTask).withNotes(NOTES_STUB).build();

        NotesCommand notesCommand = new NotesCommand(INDEX_FIRST_TASK, new Notes(editedTask.getNotes().value));

        String expectedMessage = String.format(NotesCommand.MESSAGE_ADD_REMARK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new TaskTracker(model.getTaskTracker()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(notesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(firstTask).withNotes("").build();

        NotesCommand notesCommand = new NotesCommand(INDEX_FIRST_TASK,
                new Notes(editedTask.getNotes().toString()));

        String expectedMessage = String.format(NotesCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new TaskTracker(model.getTaskTracker()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(notesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()))
                .withNotes(NOTES_STUB).build();

        NotesCommand notesCommand = new NotesCommand(INDEX_FIRST_TASK, new Notes(editedTask.getNotes().value));

        String expectedMessage = String.format(NotesCommand.MESSAGE_ADD_REMARK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new TaskTracker(model.getTaskTracker()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(notesCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        NotesCommand notesCommand = new NotesCommand(outOfBoundIndex, new Notes(VALID_REMARK_BOB));

        assertCommandFailure(notesCommand, model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskTracker().getTaskList().size());

        NotesCommand notesCommand = new NotesCommand(outOfBoundIndex, new Notes(VALID_REMARK_BOB));

        assertCommandFailure(notesCommand, model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
    }

}
