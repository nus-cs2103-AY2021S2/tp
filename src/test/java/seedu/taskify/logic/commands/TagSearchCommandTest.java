package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.taskify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskify.testutil.TypicalTasks.ALICE;
import static seedu.taskify.testutil.TypicalTasks.BENSON;
import static seedu.taskify.testutil.TypicalTasks.DANIEL;
import static seedu.taskify.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.taskify.model.Model;
import seedu.taskify.model.ModelManager;
import seedu.taskify.model.UserPrefs;
import seedu.taskify.model.task.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code TagSearchCommand}.
 */
public class TagSearchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        TagSearchCommand tagSearchFirstCommand = new TagSearchCommand(firstPredicate);
        TagSearchCommand tagSearchSecondCommand = new TagSearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(tagSearchFirstCommand.equals(tagSearchFirstCommand));

        // same values -> returns true
        TagSearchCommand tagSearchFirstCommandCopy = new TagSearchCommand(firstPredicate);
        assertTrue(tagSearchFirstCommand.equals(tagSearchFirstCommandCopy));

        // different types -> returns false
        assertFalse(tagSearchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(tagSearchFirstCommand.equals(null));

        // different commands -> returns false
        assertFalse(tagSearchFirstCommand.equals(tagSearchSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        CommandResult.setHomeTab();
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        TagSearchCommand command = new TagSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        CommandResult.setHomeTab();
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends owesMoney");
        TagSearchCommand command = new TagSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
