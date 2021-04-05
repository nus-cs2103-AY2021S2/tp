package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.pool.PooledPassengerContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_POOLS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPassengers.*;
import static seedu.address.testutil.TypicalPools.*;
import static seedu.address.testutil.TypicalPools.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPoolCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PooledPassengerContainsKeywordsPredicate firstPredicate =
                new PooledPassengerContainsKeywordsPredicate(Collections.singletonList("first"));
        PooledPassengerContainsKeywordsPredicate secondPredicate =
                new PooledPassengerContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPoolCommand findPoolFirstCommand = new FindPoolCommand(firstPredicate);
        FindPoolCommand findPoolSecondCommand = new FindPoolCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findPoolFirstCommand.equals(findPoolFirstCommand));

        // same values -> returns true
        FindPoolCommand findPoolFirstCommandCopy = new FindPoolCommand(firstPredicate);
        assertTrue(findPoolFirstCommand.equals(findPoolFirstCommandCopy));

        // different types -> returns false
        assertFalse(findPoolFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findPoolFirstCommand.equals(null));

        // different passenger -> returns false
        assertFalse(findPoolFirstCommand.equals(findPoolSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPoolFound() {
        String expectedMessage = String.format(MESSAGE_POOLS_LISTED_OVERVIEW, 0);
        PooledPassengerContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindPoolCommand command = new FindPoolCommand(predicate);
        expectedModel.updateFilteredPoolList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPoolList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePoolsFound() {
        String expectedMessage = String.format(MESSAGE_POOLS_LISTED_OVERVIEW, 3);
        PooledPassengerContainsKeywordsPredicate predicate = prepareNamePredicate("Alice Bob Carl");
        FindPoolCommand command = new FindPoolCommand(predicate);
        expectedModel.updateFilteredPoolList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOMEPOOL, OFFICEPOOL, WORKPOOL), model.getFilteredPoolList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PooledPassengerContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new PooledPassengerContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
