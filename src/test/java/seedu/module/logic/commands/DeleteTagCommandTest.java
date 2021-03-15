package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_LOW;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.module.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.ModuleBook;
import seedu.module.model.UserPrefs;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Task;
import seedu.module.testutil.TaskBuilder;

class DeleteTagCommandTest {

    private final Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    void execute_deleteTagUnfilteredList_success() {
        //get first task
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        int sz = firstTask.getTags().size();
        //get last tag of first task
        Tag lastTag = (Tag) firstTask.getTags().toArray()[sz - 1];
        String[] expectedTags = new String[firstTask.getTags().size() - 1];
        for (int i = 0; i < sz - 1; i++) { //omit the last tag
            expectedTags[i] = firstTask.getTags().iterator().next().tagName;
        }
        Task editedTask = new TaskBuilder(firstTask).withTags(expectedTags).build();

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_TASK, lastTag);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ModuleBook(model.getModuleBook()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_deleteTagFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        int sz = firstTask.getTags().size();
        //get last tag of first task
        Tag lastTag = (Tag) firstTask.getTags().toArray()[sz - 1];
        String[] expectedTags = new String[sz - 1];
        for (int i = 0; i < sz - 1; i++) {
            expectedTags[i] = firstTask.getTags().iterator().next().tagName;
        }

        Task editedTask = new TaskBuilder(firstTask).withTags(expectedTags).build();

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_TASK, lastTag);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ModuleBook(model.getModuleBook()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTagCommand remarkCommand = new DeleteTagCommand(outOfBoundIndex, new Tag(VALID_TAG_HIGH));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of module book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleBook().getTaskList().size());

        DeleteTagCommand remarkCommand = new DeleteTagCommand(outOfBoundIndex, new Tag(VALID_TAG_HIGH));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteTagCommand standardCommand = new DeleteTagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_HIGH));
        // same values -> returns true
        DeleteTagCommand commandWithSameValues = new DeleteTagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_HIGH));
        assertEquals(standardCommand, commandWithSameValues);
        // same object -> returns true
        assertEquals(standardCommand, standardCommand);
        // null -> returns false
        assertNotEquals(null, standardCommand);
        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());
        // different index -> returns false
        assertNotEquals(standardCommand, new DeleteTagCommand(INDEX_SECOND_TASK,
                new Tag(VALID_TAG_HIGH)));
        // different remark -> returns false
        assertNotEquals(standardCommand, new DeleteTagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_LOW)));
    }
}
