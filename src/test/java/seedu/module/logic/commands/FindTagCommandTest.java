package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.testutil.TypicalTasks.MIDTERM;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagCommand}.
 */
public class FindTagCommandTest {
    private Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalModuleBook(), new UserPrefs());

    @Test
    public void equals() {
        Tag firstTag =
                new Tag("first");
        Tag secondTag =
                new Tag("second");

        FindTagCommand findFirstCommand = new FindTagCommand(firstTag);
        FindTagCommand findSecondCommand = new FindTagCommand(secondTag);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTagCommand findFirstCommandCopy = new FindTagCommand(firstTag);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        Tag pred = new Tag("noneOfTheTagsGotThisGodlyName");
        FindTagCommand command = new FindTagCommand(pred);
        expectedModel.updateFilteredTaskList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        Tag pred = new Tag("highPriority");
        FindTagCommand command = new FindTagCommand(pred);
        expectedModel.updateFilteredTaskList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MIDTERM), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
