package seedu.heymatez.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_TASK_LIST;
import static seedu.heymatez.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.task.PriorityContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code FindByPriorityCommand}.
 */
public class FindByPriorityCommandTest {
    private Model model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalHeyMatez(), new UserPrefs());

    @Test
    public void equals() {
        PriorityContainsKeywordPredicate firstPredicate =
                new PriorityContainsKeywordPredicate(Collections.singletonList("first"));
        PriorityContainsKeywordPredicate secondPredicate =
                new PriorityContainsKeywordPredicate(Collections.singletonList("second"));

        FindByPriorityCommand findFirstCommand = new FindByPriorityCommand(firstPredicate);
        FindByPriorityCommand findSecondCommand = new FindByPriorityCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByPriorityCommand findFirstCommandCopy = new FindByPriorityCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = MESSAGE_EMPTY_TASK_LIST;

        PriorityContainsKeywordPredicate predicate = preparePredicate(" ");

        FindByPriorityCommand command = new FindByPriorityCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_singleKeywords_taskFound() {
        String expectedMessage = MESSAGE_EMPTY_TASK_LIST;

        PriorityContainsKeywordPredicate highPredicate = preparePredicate("high");
        FindByPriorityCommand highCommand = new FindByPriorityCommand(highPredicate);
        expectedModel.updateFilteredTaskList(highPredicate);

        assertCommandSuccess(highCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());


        PriorityContainsKeywordPredicate mediumPredicate = preparePredicate("medium");
        FindByPriorityCommand medCommand = new FindByPriorityCommand(mediumPredicate);

        expectedModel.updateFilteredTaskList(mediumPredicate);
        assertCommandSuccess(medCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());


        PriorityContainsKeywordPredicate lowPredicate = preparePredicate("low");
        FindByPriorityCommand command = new FindByPriorityCommand(lowPredicate);
        expectedModel.updateFilteredTaskList(lowPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());

        PriorityContainsKeywordPredicate unassignedPredicate = preparePredicate("unassigned");
        FindByPriorityCommand unassCommand = new FindByPriorityCommand(unassignedPredicate);

        expectedModel.updateFilteredTaskList(unassignedPredicate);
        assertCommandSuccess(unassCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code DetailsContainsKeywordsPredicate}.
     */
    private PriorityContainsKeywordPredicate preparePredicate(String userInput) {
        return new PriorityContainsKeywordPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
