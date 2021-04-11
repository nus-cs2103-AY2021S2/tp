package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_POOLS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRST_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRST_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.prepareNamePredicate;
import static seedu.address.testutil.TypicalPools.HOMEPOOL;
import static seedu.address.testutil.TypicalPools.OFFICEPOOL;
import static seedu.address.testutil.TypicalPools.WORKPOOL;
import static seedu.address.testutil.TypicalPools.getTypicalAddressBookPools;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pool.PooledPassengerContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code FindPoolCommand}.
 */
public class FindPoolCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookPools(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookPools(), new UserPrefs());

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
    public void execute_oneUnmatchedKeyword_noPoolFound() {
        String expectedMessage = String.format(MESSAGE_POOLS_LISTED_OVERVIEW, 0);
        PooledPassengerContainsKeywordsPredicate predicate = prepareNamePredicate(VALID_FIRST_NAME_BOB);
        FindPoolCommand command = new FindPoolCommand(predicate);
        expectedModel.updateFilteredPoolList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPoolList());
    }

    @Test
    public void execute_multipleMatchedKeywords_multiplePoolsFound() {
        String expectedMessage = String.format(MESSAGE_POOLS_LISTED_OVERVIEW, 3);
        PooledPassengerContainsKeywordsPredicate predicate = prepareNamePredicate("Alice Bob Daniel");
        FindPoolCommand command = new FindPoolCommand(predicate);
        expectedModel.updateFilteredPoolList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOMEPOOL, OFFICEPOOL, WORKPOOL), model.getFilteredPoolList());
    }

    @Test
    public void equals() {
        PooledPassengerContainsKeywordsPredicate firstPredicate =
                new PooledPassengerContainsKeywordsPredicate(Collections.singletonList(VALID_FIRST_NAME_AMY));
        PooledPassengerContainsKeywordsPredicate secondPredicate =
                new PooledPassengerContainsKeywordsPredicate(Collections.singletonList(VALID_FIRST_NAME_BOB));

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
}
