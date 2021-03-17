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
import static seedu.module.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import java.util.Set;

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



class TagCommandTest {

    private static final String TAG_STUB = "TAG";

    private final Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    void execute_addTagUnfilteredList_success() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        String[] expectedTags = new String[firstTask.getTags().size() + 1];
        for (int i = 0; i < firstTask.getTags().size(); i++) {
            expectedTags[i] = firstTask.getTags().iterator().next().tagName;
        }
        expectedTags[firstTask.getTags().size()] = TAG_STUB;
        Task editedTask = new TaskBuilder(firstTask).withTags(expectedTags).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, new Tag(TAG_STUB));

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ModuleBook(model.getModuleBook()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_addTagFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Set<Tag> tags = firstTask.getTags();
        String[] expectedTags = new String[tags.size() + 1];
        for (int i = 0; i < tags.size(); i++) {
            expectedTags[i] = tags.iterator().next().tagName;
        }
        expectedTags[tags.size()] = TAG_STUB;
        Task editedTask = new TaskBuilder(firstTask).withTags(expectedTags).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, new Tag(TAG_STUB));

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ModuleBook(model.getModuleBook()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TagCommand remarkCommand = new TagCommand(outOfBoundIndex, new Tag(VALID_TAG_PRIORITY_HIGH));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleBook().getTaskList().size());

        TagCommand remarkCommand = new TagCommand(outOfBoundIndex, new Tag(VALID_TAG_PRIORITY_HIGH));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_PRIORITY_HIGH));
        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_PRIORITY_HIGH));
        assertEquals(standardCommand, commandWithSameValues);
        // same object -> returns true
        assertEquals(standardCommand, standardCommand);
        // null -> returns false
        assertNotEquals(null, standardCommand);
        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());
        // different index -> returns false
        assertNotEquals(standardCommand, new TagCommand(INDEX_SECOND_TASK,
                new Tag(VALID_TAG_PRIORITY_HIGH)));
        // different remark -> returns false
        assertNotEquals(standardCommand, new TagCommand(INDEX_FIRST_TASK,
                new Tag(VALID_TAG_PRIORITY_LOW)));
    }
}
