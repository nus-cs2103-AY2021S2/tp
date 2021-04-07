package seedu.iscam.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static seedu.iscam.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.iscam.testutil.TypicalClients.CARL;
import static seedu.iscam.testutil.TypicalClients.ELLE;
import static seedu.iscam.testutil.TypicalClients.FIONA;
import static seedu.iscam.testutil.TypicalClients.getTypicalClientBook;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.client.PlanContainsKeywordsPredicate;
import seedu.iscam.model.user.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPlansCommandTest {
    private Model model = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalClientBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void equals() {
        PlanContainsKeywordsPredicate firstPredicate =
                new PlanContainsKeywordsPredicate(Collections.singletonList("first"));
        PlanContainsKeywordsPredicate secondPredicate =
                new PlanContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPlansCommand findFirstPlanCommand = new FindPlansCommand(firstPredicate);
        FindPlansCommand findSecondPlanCommand = new FindPlansCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstPlanCommand.equals(findFirstPlanCommand));

        // same values -> returns true
        FindPlansCommand findFirstPlanCommandCopy = new FindPlansCommand(firstPredicate);
        assertTrue(findFirstPlanCommand.equals(findFirstPlanCommandCopy));

        // different types -> returns false
        assertFalse(findFirstPlanCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstPlanCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstPlanCommand.equals(findSecondPlanCommand));
    }

    @Test
    public void execute_zeroKeywords_noClientFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 0);
        PlanContainsKeywordsPredicate predicate = preparePlanPredicate(" ");
        FindPlansCommand command = new FindPlansCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3);
        PlanContainsKeywordsPredicate predicate = preparePlanPredicate("Life Protect MediShield");
        FindPlansCommand command = new FindPlansCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // Returns true if CARL, ELLE, FIONA are displayed
        // because they contain the plans with the search keyword.
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code PlanContainsKeywordsPredicate}.
     */
    private PlanContainsKeywordsPredicate preparePlanPredicate(String userInput) {
        return new PlanContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
