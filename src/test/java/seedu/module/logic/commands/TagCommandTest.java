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

import java.util.HashSet;
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

    private static final String TAG_STUB = "TagA";
    private static final String TAG_STUB_MULTIPLE = "TagB";

    private final Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    void execute_addTagUnfilteredList_success() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        String[] expectedTags = new String[firstTask.getTags().size() + 1];
        for (int i = 0; i < firstTask.getTags().size(); i++) {
            expectedTags[i] = firstTask.getTags().iterator().next().tagName;
        }
        expectedTags[expectedTags.length - 1] = TAG_STUB;
        Task editedTask = new TaskBuilder(firstTask).withTags(expectedTags).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK);
        Set<Tag> tagStubs = new HashSet<>();
        tagStubs.add(new Tag(TAG_STUB));
        tagCommand.setTags(tagStubs);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ModuleBook(model.getModuleBook()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_addTwoTagsUnfilteredList_success() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        String[] expectedTags = new String[firstTask.getTags().size() + 2];
        for (int i = 0; i < firstTask.getTags().size(); i++) {
            expectedTags[i] = firstTask.getTags().iterator().next().tagName;
        }
        expectedTags[expectedTags.length - 1] = TAG_STUB;
        expectedTags[expectedTags.length - 2] = TAG_STUB_MULTIPLE;
        Task editedTask = new TaskBuilder(firstTask).withTags(expectedTags).build();

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK);
        Set<Tag> tagStubs = new HashSet<>();
        tagStubs.add(new Tag(TAG_STUB));
        tagStubs.add(new Tag(TAG_STUB_MULTIPLE));
        tagCommand.setTags(tagStubs);

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

        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK);
        Set<Tag> tagStubs = new HashSet<>();
        tagStubs.add(new Tag(TAG_STUB));
        tagCommand.setTags(tagStubs);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new ModuleBook(model.getModuleBook()), new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TagCommand tagCommand = new TagCommand(outOfBoundIndex);
        Set<Tag> tagStubs = new HashSet<>();
        tagStubs.add(new Tag(VALID_TAG_HIGH));
        tagCommand.setTags(tagStubs);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of module book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleBook().getTaskList().size());

        TagCommand tagCommand = new TagCommand(outOfBoundIndex);
        Set<Tag> tagStubs = new HashSet<>();
        tagStubs.add(new Tag(VALID_TAG_HIGH));
        tagCommand.setTags(tagStubs);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Tag> tagStubs = new HashSet<>();
        tagStubs.add(new Tag(VALID_TAG_HIGH));

        Set<Tag> tagStubsAlter = new HashSet<>();
        tagStubs.add(new Tag(VALID_TAG_HIGH));
        tagStubs.add(new Tag(VALID_TAG_LOW));

        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_TASK);
        standardCommand.setTags(tagStubs);
        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(INDEX_FIRST_TASK);
        commandWithSameValues.setTags(tagStubs);
        assertEquals(standardCommand, commandWithSameValues);
        // same object -> returns true
        assertEquals(standardCommand, standardCommand);
        // null -> returns false
        assertNotEquals(null, standardCommand);
        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());
        // different index -> returns false
        TagCommand commandWithDifferentIndex = new TagCommand(INDEX_SECOND_TASK);
        commandWithDifferentIndex.setTags(tagStubs);
        assertNotEquals(standardCommand, commandWithDifferentIndex);
        // different tags -> returns false
        TagCommand commandWithDifferentTags = new TagCommand(INDEX_FIRST_TASK);
        commandWithDifferentTags.setTags(tagStubsAlter);
        assertNotEquals(standardCommand, commandWithDifferentTags);
    }
}
