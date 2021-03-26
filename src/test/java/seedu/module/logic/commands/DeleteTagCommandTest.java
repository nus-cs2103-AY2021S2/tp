package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_LOW;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.module.testutil.TypicalIndexes.INDEX_FOURTH_TASK;
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
    void execute_deleteTagUnfilteredListWithStartTime_success() {
        //get first task, which has a startTime field
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
    void execute_deleteTagUnfilteredListWithoutStartTime_success() {
        //get fourth task, which does not have a startTime field
        Task fourthTask = model.getFilteredTaskList().get(INDEX_FOURTH_TASK.getZeroBased());
        int sz = fourthTask.getTags().size();
        //get last tag of the fourth task
        Tag lastTag = (Tag) fourthTask.getTags().toArray()[sz - 1];
        String[] expectedTags = new String[fourthTask.getTags().size() - 1];
        for (int i = 0; i < sz - 1; i++) { //omit the last tag
            expectedTags[i] = fourthTask.getTags().iterator().next().tagName;
        }
        Task editedTask = new TaskBuilder(fourthTask).withTags(expectedTags).build();

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FOURTH_TASK, lastTag);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ModuleBook(model.getModuleBook()), new UserPrefs());
        expectedModel.setTask(fourthTask, editedTask);

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
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, new Tag(VALID_TAG_PRIORITY_HIGH));

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of module book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleBook().getTaskList().size());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, new Tag(VALID_TAG_PRIORITY_HIGH));

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_tagNotExist_throwsCommandException() {
        Tag stubTag = new Tag(VALID_TAG_PRIORITY_HIGH);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_TASK, stubTag);
        assertCommandFailure(deleteTagCommand, model, DeleteTagCommand.MESSAGE_TAG_NOT_EXISTS);
    }

    @Test
    public void equals() {
        final DeleteTagCommand standardCommand = new DeleteTagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_PRIORITY_HIGH));
        // same values -> returns true
        DeleteTagCommand commandWithSameValues = new DeleteTagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_PRIORITY_HIGH));
        assertEquals(standardCommand, commandWithSameValues);
        // same object -> returns true
        assertEquals(standardCommand, standardCommand);
        // null -> returns false
        assertNotEquals(null, standardCommand);
        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());
        // different index -> returns false
        assertNotEquals(standardCommand, new DeleteTagCommand(INDEX_SECOND_TASK,
                new Tag(VALID_TAG_PRIORITY_HIGH)));
        // different remark -> returns false
        assertNotEquals(standardCommand, new DeleteTagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_PRIORITY_LOW)));
    }
}
