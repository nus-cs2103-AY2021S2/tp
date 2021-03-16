package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.commons.core.Messages.MESSAGE_ENDPOINTS_LISTED_OVERVIEW;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.us.among.testutil.TypicalEndpoints.HEAD;
import static seedu.us.among.testutil.TypicalEndpoints.OPTIONS;
import static seedu.us.among.testutil.TypicalEndpoints.PUT;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.EndPointContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEndpointList(), new UserPrefs());

    @Test
    public void equals() {
        EndPointContainsKeywordsPredicate firstPredicate = new EndPointContainsKeywordsPredicate(
                Collections.singletonList("first"));
        EndPointContainsKeywordsPredicate secondPredicate = new EndPointContainsKeywordsPredicate(
                Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different endpoint -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEndpointFound() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 0);
        EndPointContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEndpointList());
    }

    @Test
    public void execute_multipleKeywords_multipleEndpointsFound() {
        String expectedMessage = String.format(MESSAGE_ENDPOINTS_LISTED_OVERVIEW, 3);
        EndPointContainsKeywordsPredicate predicate = preparePredicate("put head options");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEndpointList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PUT, HEAD, OPTIONS), model.getFilteredEndpointList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private EndPointContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EndPointContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
